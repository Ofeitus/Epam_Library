package com.epam.ofeitus.library.dao;

import com.epam.ofeitus.library.dao.impl.MySqlLibraryDao;

public class DaoFactory {
    private static final DaoFactory instance = new DaoFactory();

    private final LibraryDao libraryDAO = new MySqlLibraryDao();

    private DaoFactory() {
    }

    public LibraryDao getApplianceDAO() {
        return libraryDAO;
    }

    public static DaoFactory getInstance() {
        return instance;
    }
}
