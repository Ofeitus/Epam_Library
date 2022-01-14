package com.epam.ofeitus.library.service.impl;

import com.epam.ofeitus.library.dao.UserDao;
import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.dao.factory.impl.MySqlDaoFactory;
import com.epam.ofeitus.library.entity.user.User;
import com.epam.ofeitus.library.entity.user.constituent.UserRole;
import com.epam.ofeitus.library.service.UserService;
import com.epam.ofeitus.library.service.exception.ServiceException;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public User login(String email, String password) throws ServiceException {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();

        User user = null;
        try {
            User userFromDB = userDao.findByEmail(email);
            if (userFromDB != null && userFromDB.getPasswordHash().equals(DigestUtils.sha256Hex(password))) {
                user = userFromDB;
            }
        } catch (DaoException e) {
            throw new ServiceException("Unable to retrieve user from DB.", e);
        }

        return user;
    }

    @Override
    public void register(String firstName, String lastName, String email, String password) throws ServiceException {
        // TODO Validation
        User user = new User(0, firstName, lastName, "", email, DigestUtils.sha256Hex(password), UserRole.MEMBER);
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();
        try {
            userDao.save(user);
        } catch (DaoException e) {
            throw new ServiceException("Unable to save new user to Data Source.", e);
        }
    }

    @Override
    public List<User> getAll() throws ServiceException {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User getByEmail(String email) throws ServiceException {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();
        try {
            return userDao.findByEmail(email);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int editPersonalData(int id, String name, String surname, String phoneNumber) throws ServiceException {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();
        try {
            User user = userDao.findById(id);
            user.setName(name);
            user.setSurname(surname);
            user.setPhoneNumber(phoneNumber);
            return userDao.update(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> getAllMembers(int page, int itemsOnPage) throws ServiceException {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();
        try {
            int offset = (page - 1) * itemsOnPage;

            return userDao.findByRoleId(UserRole.MEMBER.ordinal() + 1, offset, itemsOnPage);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int countAllMembers() throws ServiceException {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();
        try {
            return userDao.countByRoleId(UserRole.MEMBER.ordinal() + 1);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User getByUserId(int userId) throws ServiceException {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();
        try {
            return userDao.findById(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> getMemberBySearchRequest(int userId, String email, int page, int itemsOnPage) throws ServiceException {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();
        try {
            int offset = (page - 1) * itemsOnPage;

            return userDao.findBySearchRequest(userId, email, offset, itemsOnPage);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int countMembersBySearchRequest(int userId, String email) throws ServiceException {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();
        try {
            return userDao.countBySearchRequest(userId, email);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
