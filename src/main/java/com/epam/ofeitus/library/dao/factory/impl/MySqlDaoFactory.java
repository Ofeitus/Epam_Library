package com.epam.ofeitus.library.dao.factory.impl;

import com.epam.ofeitus.library.dao.*;
import com.epam.ofeitus.library.dao.factory.DaoFactory;
import com.epam.ofeitus.library.dao.impl.*;

public class MySqlDaoFactory implements DaoFactory {
    private static final MySqlDaoFactory instance = new MySqlDaoFactory();

    private static final AuthorDao mySqlAuthorDao = new MySqlAuthorDao();
    private static final BookCategoryDao mySqlBookCategoryDao = new MySqlBookCategoryDao();
    private static final BookDao mySqlBookDao = new MySqlBookDao();
    private static final CopyOfBookDao mySqlCopyOfBookDao = new MySqlCopyOfBookDao();
    private static final FinePaymentDao mySqlFinePaymentDao = new MySqlFinePaymentDao();
    private static final LoanDao mySqlLoanDao = new MySqlLoanDao();
    private static final ReservationDao mySqlReservationDao = new MySqlReservationDao();
    private static final UserDao mySqlUserDao = new MySqlUserDao();

    private MySqlDaoFactory() {
    }

    public static MySqlDaoFactory getInstance() {
        return instance;
    }

    @Override
    public AuthorDao getAuthorDao() {
        return mySqlAuthorDao;
    }

    @Override
    public BookCategoryDao getBookCategoryDao() {
        return mySqlBookCategoryDao;
    }

    @Override
    public BookDao getBookDao() {
        return mySqlBookDao;
    }

    @Override
    public CopyOfBookDao getCopyOfBookDao() {
        return mySqlCopyOfBookDao;
    }

    @Override
    public FinePaymentDao getFineDao() {
        return mySqlFinePaymentDao;
    }

    @Override
    public LoanDao getLoanDao() {
        return mySqlLoanDao;
    }

    @Override
    public ReservationDao getReservationDao() {
        return mySqlReservationDao;
    }

    @Override
    public UserDao getUserDao() {
        return mySqlUserDao;
    }
}
