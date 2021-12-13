package com.epam.ofeitus.library.service.impl;

import com.epam.ofeitus.library.dao.UserDao;
import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.dao.factory.impl.MySqlDaoFactory;
import com.epam.ofeitus.library.entity.user.User;
import com.epam.ofeitus.library.service.UserService;
import com.epam.ofeitus.library.service.exception.ServiceException;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public User login(String email, String password) throws ServiceException {
        return null;
    }

    @Override
    public boolean register(String firstName, String lastName, String email, String password) throws ServiceException {
        return false;
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
