package com.epam.ofeitus.library.service.factory;

import com.epam.ofeitus.library.service.BookService;
import com.epam.ofeitus.library.service.UserService;
import com.epam.ofeitus.library.service.impl.BookServiceImpl;
import com.epam.ofeitus.library.service.impl.UserServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private static final UserService userService = new UserServiceImpl();
    private static final BookService bookService = new BookServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public BookService getBookService() {
        return bookService;
    }
}
