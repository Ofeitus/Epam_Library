package com.epam.ofeitus.library.service.impl;

import com.epam.ofeitus.library.dao.UserDao;
import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.dao.factory.DaoFactory;
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
        if (email == null || password == null) {
            return null;
        }
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();

        User user = null;
        try {
            User userFromDB = userDao.findByEmail(email);
            if (userFromDB != null && userFromDB.getPasswordHash().equals(DigestUtils.sha256Hex(password))) {
                user = userFromDB;
            }
        } catch (DaoException e) {
            // TODO logger.error("Unable to retrieve user from DB. {}", e.getMessage());
            throw new ServiceException("Unable to retrieve user from DB.", e);
        }

        return user;
    }

    @Override
    public void register(String firstName, String lastName, String email, String password) throws ServiceException {
        User user = new User(0, firstName, lastName, email, password, UserRole.MEMBER);
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao();
        try {
            userDao.save(user);
        } catch (DaoException e) {
            // TODO logger.error("Unable to save new user to Data Source. {}", e.getMessage());
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
}
