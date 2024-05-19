package com.epam.ofeitus.library.service.factory;

import com.epam.ofeitus.library.service.SubjectService;
import com.epam.ofeitus.library.service.UserService;
import com.epam.ofeitus.library.service.impl.SubjectServiceImpl;
import com.epam.ofeitus.library.service.impl.UserServiceImpl;

/**
 * Factory, that provides services.
 */
public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private static final UserService userService = new UserServiceImpl();
    private static final SubjectService SUBJECT_SERVICE = new SubjectServiceImpl();

    private ServiceFactory() {
    }

    /**
     * Gets instance.
     *
     * @return instance of ServiceFactory
     */
    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public SubjectService getBookService() {
        return SUBJECT_SERVICE;
    }
}
