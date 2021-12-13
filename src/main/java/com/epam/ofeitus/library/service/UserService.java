package com.epam.ofeitus.library.service;

import com.epam.ofeitus.library.entity.user.User;
import com.epam.ofeitus.library.service.exception.ServiceException;

import java.util.List;

public interface UserService {
    User login(String email, String password) throws ServiceException;

    boolean register(String firstName, String lastName, String email, String password) throws ServiceException;

    List<User> getAll() throws ServiceException;
}
