package com.epam.ofeitus.library.dao.factory;

import com.epam.ofeitus.library.dao.BookDao;
import com.epam.ofeitus.library.dao.UserDao;
import com.epam.ofeitus.library.dao.impl.MySqlBookDao;

import java.sql.Connection;

public class MySqlDaoFactory implements DaoFactory{
    private static final MySqlDaoFactory instance = new MySqlDaoFactory();

    private static final BookDao mySqlBookDao = new MySqlBookDao();

    private MySqlDaoFactory() {
    }

    public static MySqlDaoFactory getInstance() {
        return instance;
    }

    @Override
    public BookDao getBookDao(Connection connection) {
        return mySqlBookDao;
    }

    @Override
    public UserDao getUserDao(Connection connection) {
        return null;
    }
}
