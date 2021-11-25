package com.epam.ofeitus.library.dao.factory;

import com.epam.ofeitus.library.dao.BookDao;
import com.epam.ofeitus.library.dao.UserDao;

import java.sql.Connection;

public interface DaoFactory {
    BookDao getBookDao(Connection connection);
    UserDao getUserDao(Connection connection);
}
