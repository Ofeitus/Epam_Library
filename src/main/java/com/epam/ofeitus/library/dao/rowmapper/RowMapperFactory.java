package com.epam.ofeitus.library.dao.rowmapper;

import com.epam.ofeitus.library.dao.rowmapper.impl.*;

public class RowMapperFactory {
    public static final AuthorRowMapper authorRowMapper = new AuthorRowMapper();
    public static final BookCategoryRowMapper bookCategoryRowMapper = new BookCategoryRowMapper();
    public static final BookRowMapper bookRowMapper = new BookRowMapper();
    public static final CopyOfBookRowMapper copyOfBookRowMapper = new CopyOfBookRowMapper();
    public static final LoanRowMapper loanRowMapper = new LoanRowMapper();
    public static final ReservationRowMapper reservationRowMapper = new ReservationRowMapper();
    public static final UserRowMapper userRowMapper = new UserRowMapper();

    public static AuthorRowMapper getAuthorRowMapper() {
        return authorRowMapper;
    }

    public static BookCategoryRowMapper getBookCategoryRowMapper() {
        return bookCategoryRowMapper;
    }

    public static BookRowMapper getBookRowMapper() {
        return bookRowMapper;
    }

    public static CopyOfBookRowMapper getCopyOfBookRowMapper() {
        return copyOfBookRowMapper;
    }

    public static LoanRowMapper getLoanRowMapper() {
        return loanRowMapper;
    }

    public static ReservationRowMapper getReservationRowMapper() {
        return reservationRowMapper;
    }

    public static UserRowMapper getUserRowMapper() {
        return userRowMapper;
    }
}
