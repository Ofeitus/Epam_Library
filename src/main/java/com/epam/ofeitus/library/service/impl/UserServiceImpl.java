package com.epam.ofeitus.library.service.impl;

import com.epam.ofeitus.library.dao.UserDao;
import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.dao.factory.impl.MySqlDaoFactory;
import com.epam.ofeitus.library.entity.User;
import com.epam.ofeitus.library.entity.UserRole;
import com.epam.ofeitus.library.service.UserService;
import com.epam.ofeitus.library.service.exception.ServiceException;

import com.epam.ofeitus.library.service.validator.EntityValidator;
import com.epam.ofeitus.library.service.validator.ValidationPattern;
import com.epam.ofeitus.library.service.validator.ValidatorFactory;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.*;
import java.util.regex.Pattern;

public class UserServiceImpl implements UserService {
    @Override
    public boolean register(String firstName, String lastName, String email, String password) throws ServiceException {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();
        EntityValidator<User> userValidator = ValidatorFactory.getInstance().getUserValidator();

        User user = new User(0, new Date(), firstName, lastName, "", email, DigestUtils.sha256Hex(password), UserRole.MEMBER, false);

        if (password == null ||
                !Pattern.compile(ValidationPattern.PASSWORD_PATTERN).matcher(password).matches() ||
                !userValidator.validate(user)) {
            return false;
        }

        try {
            return userDao.save(user) == 1;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User login(String email, String password) throws ServiceException {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();

        User user = null;
        try {
            User userFromDB = userDao.findByEmail(email);
            if (userFromDB != null && !userFromDB.isDeleted() && userFromDB.getPasswordHash().equals(DigestUtils.sha256Hex(password))) {
                user = userFromDB;
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return user;
    }

    @Override
    public boolean editPersonalData(int userId, String name, String surname, String phoneNumber) throws ServiceException {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();
        EntityValidator<User> userValidator = ValidatorFactory.getInstance().getUserValidator();

        try {
            User user = userDao.findExistingById(userId);
            user.setName(name);
            user.setSurname(surname);
            user.setPhoneNumber(phoneNumber);

            if (!userValidator.validate(user)) {
                return false;
            }

            return userDao.update(user) == 1;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteUser(int userId) throws ServiceException {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();

        try {
            User user = userDao.findExistingById(userId);
            if (user.getUserRole() == UserRole.ADMIN) {
                throw new ServiceException("Can't delete.");
            }
            userDao.deleteById(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void restoreUser(int userId) throws ServiceException {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();

        try {
            User user = userDao.findById(userId);
            if (user.getUserRole() == UserRole.ADMIN) {
                throw new ServiceException("Can't restore.");
            }
            userDao.restoreById(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void setRole(int userId, int roleId) throws ServiceException {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();

        try {
            User user = userDao.findById(userId);
            if (user.getUserRole() == UserRole.ADMIN || roleId == 1) {
                throw new ServiceException("Can't change role.");
            }
            user.setUserRole(UserRole.values()[roleId - 1]);
            userDao.update(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User getByUserId(int userId) throws ServiceException {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();

        try {
            return userDao.findExistingById(userId);
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
    public List<User> getAll(int page, int itemsOnPage) throws ServiceException {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();

        try {
            int offset = (page - 1) * itemsOnPage;

            return userDao.findAll(offset, itemsOnPage);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int countAll() throws ServiceException {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();

        try {
            return userDao.countAll();
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
    public List<User> getUsersBySearchRequest(int userRoleId, int userId, String email, int page, int itemsOnPage) throws ServiceException {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();

        try {
            int offset = (page - 1) * itemsOnPage;

            return userDao.findBySearchRequest(userRoleId, userId, email, offset, itemsOnPage);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int countUsersBySearchRequest(int userRoleId, int userId, String email) throws ServiceException {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();

        try {
            return userDao.countBySearchRequest(userRoleId, userId, email, new Date());
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
