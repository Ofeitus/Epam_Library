package com.epam.ofeitus.library.service.impl;

import com.epam.ofeitus.library.dao.UserDao;
import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.dao.factory.impl.MySqlDaoFactory;
import com.epam.ofeitus.library.entity.report.UserCompositionReport;
import com.epam.ofeitus.library.entity.user.User;
import com.epam.ofeitus.library.entity.user.constituent.UserRole;
import com.epam.ofeitus.library.service.UserService;
import com.epam.ofeitus.library.service.exception.ServiceException;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.*;

public class UserServiceImpl implements UserService {
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
            throw new ServiceException("Unable to retrieve user from DB.", e);
        }

        return user;
    }

    @Override
    public void register(String firstName, String lastName, String email, String password) throws ServiceException {
        // TODO Validation
        User user = new User(0, new Date(), firstName, lastName, "", email, DigestUtils.sha256Hex(password), UserRole.MEMBER, false);
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();
        try {
            userDao.save(user);
        } catch (DaoException e) {
            throw new ServiceException("Unable to save new user to Data Source.", e);
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

    @Override
    public int setRole(int userId, int roleId) throws ServiceException {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();
        try {
            User user = userDao.findById(userId);
            user.setUserRole(UserRole.values()[roleId - 1]);
            return userDao.update(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int deleteUser(int userId) throws ServiceException {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();
        try {
            return userDao.deleteById(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int restoreUser(int userId) throws ServiceException {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();
        try {
            return userDao.restoreById(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public UserCompositionReport getUserCompositionReport(Date fromDate, Date toDate) throws ServiceException {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();
        try {
            UserCompositionReport userCompositionReport = new UserCompositionReport(
                    userDao.countBySearchRequest(0, 0, "", fromDate),
                    userDao.countBySearchRequest(0, 0, "", toDate),
                    userDao.countBySearchRequest(1, 0, "", fromDate),
                    userDao.countBySearchRequest(1, 0, "", toDate),
                    userDao.countBySearchRequest(2, 0, "", fromDate),
                    userDao.countBySearchRequest(2, 0, "", toDate),
                    userDao.countBySearchRequest(3, 0, "", fromDate),
                    userDao.countBySearchRequest(3, 0, "", toDate),
                    null,
                    null
            );

            List<Date> dynamicsDates = new ArrayList<>();
            List<Integer> dynamicsValues = new ArrayList<>();

            Calendar start = Calendar.getInstance();
            start.setTime(fromDate);
            Calendar end = Calendar.getInstance();
            end.setTime(toDate);

            for (Date date = start.getTime(); start.before(end); start.add(Calendar.MONTH, 1), date = start.getTime()) {
                dynamicsDates.add(date);
                dynamicsValues.add(userDao.countBySearchRequest(3, 0, "", date));
            }

            dynamicsDates.add(new Date());
            dynamicsValues.add(userDao.countBySearchRequest(3, 0, "", new Date()));

            userCompositionReport.setDynamicsDates(dynamicsDates);
            userCompositionReport.setDynamicsValues(dynamicsValues);

            return userCompositionReport;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
