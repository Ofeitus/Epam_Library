package com.epam.ofeitus.library.service.factory;

import com.epam.ofeitus.library.service.UserService;
import com.epam.ofeitus.library.service.impl.UserServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private static final UserService userService = new UserServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }
}
