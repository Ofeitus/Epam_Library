package com.epam.ofeitus.library.dao.factory.impl;

import com.epam.ofeitus.library.dao.*;
import com.epam.ofeitus.library.dao.factory.DaoFactory;
import com.epam.ofeitus.library.dao.impl.*;

/**
 * Factory, that provides dao's.
 */
public class MySqlDaoFactory implements DaoFactory {
    private static final MySqlDaoFactory instance = new MySqlDaoFactory();

    private static final SubjectDao mySqlSubjectDao = new MySqlSubjectDao();
    private static final UserDao mySqlUserDao = new MySqlUserDao();

    private MySqlDaoFactory() {
    }

    /**
     * Gets instance.
     *
     * @return instance of ServiceFactory
     */
    public static MySqlDaoFactory getInstance() {
        return instance;
    }

    @Override
    public SubjectDao getSubjectDao() {
        return mySqlSubjectDao;
    }

    @Override
    public UserDao getUserDao() {
        return mySqlUserDao;
    }
}
