package com.epam.ofeitus.library.dao.factory;

import com.epam.ofeitus.library.dao.*;

public interface DaoFactory {
    BookDao getBookDao();

    BookCategoryDao getBookCategoryDao();

    CopyOfBookDao getCopyOfBookDao();

    AuthorDao getAuthorDao();

    UserDao getUserDao();

    LoanDao getLoanDao();

    ReservationDao getReservationDao();
}
