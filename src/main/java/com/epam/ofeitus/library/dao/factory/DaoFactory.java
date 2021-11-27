package com.epam.ofeitus.library.dao.factory;

import com.epam.ofeitus.library.dao.*;
import com.epam.ofeitus.library.entity.user.FinePayment;

public interface DaoFactory {
    BookDao getBookDao();

    BookCategoryDao getBookCategoryDao();

    AuthorDao getAuthorDao();

    UserDao getUserDao();

    LoanDao getLoanDao();

    ReservationDao getReservationDao();

    FinePaymentDao getFineDao();
}
