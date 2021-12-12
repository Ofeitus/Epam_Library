package com.epam.ofeitus.library.service.impl;

import com.epam.ofeitus.library.entity.user.User;
import com.epam.ofeitus.library.service.UserService;
import com.epam.ofeitus.library.service.exception.ServiceException;

public class UserServiceImpl implements UserService {
    @Override
    public User login(String email, String password) throws ServiceException {
        return null;
    }

    @Override
    public boolean register(String firstName, String lastName, String email, String password) throws ServiceException {
        return false;
    }
}
