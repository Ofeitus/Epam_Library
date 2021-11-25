package com.epam.ofeitus.library.dao.factory;

import com.epam.ofeitus.library.dao.*;

public interface DaoFactory {
    BookDao getBookDao();
    AuthorDao getAuthorDao();
    UserDao getUserDao();
    LoanDao getLoanDao();
    ReservationDao getReservationDao();
}
