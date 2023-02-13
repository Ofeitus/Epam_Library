package com.epam.ofeitus.library.dao.rowmapper;

import com.epam.ofeitus.library.dao.rowmapper.impl.*;
import com.epam.ofeitus.library.entity.bank.Account;
import com.epam.ofeitus.library.entity.bank.Credit;
import com.epam.ofeitus.library.entity.bank.Deposit;

/**
 * Factory, that provides row mappers.
 */
public class RowMapperFactory {
    private static final RowMapperFactory instance = new RowMapperFactory();

    private static final AuthorRowMapper authorRowMapper = new AuthorRowMapper();
    private static final BookCategoryRowMapper bookCategoryRowMapper = new BookCategoryRowMapper();
    private static final BookRowMapper bookRowMapper = new BookRowMapper();
    private static final CopyOfBookRowMapper copyOfBookRowMapper = new CopyOfBookRowMapper();
    private static final LoanRowMapper loanRowMapper = new LoanRowMapper();
    private static final ReservationRowMapper reservationRowMapper = new ReservationRowMapper();
    private static final UserRowMapper userRowMapper = new UserRowMapper();
    private static final AccountRowMapper accountRowMapper = new AccountRowMapper();
    private static final DepositRowMapper depositRowMapper = new DepositRowMapper();
    private static final CreditRowMapper creditRowMapper = new CreditRowMapper();

    private RowMapperFactory() {
    }

    /**
     * Gets instance.
     *
     * @return instance of RowMapperFactory
     */
    public static RowMapperFactory getInstance() {
        return instance;
    }

    public AuthorRowMapper getAuthorRowMapper() {
        return authorRowMapper;
    }

    public BookCategoryRowMapper getBookCategoryRowMapper() {
        return bookCategoryRowMapper;
    }

    public BookRowMapper getBookRowMapper() {
        return bookRowMapper;
    }

    public CopyOfBookRowMapper getCopyOfBookRowMapper() {
        return copyOfBookRowMapper;
    }

    public LoanRowMapper getLoanRowMapper() {
        return loanRowMapper;
    }

    public ReservationRowMapper getReservationRowMapper() {
        return reservationRowMapper;
    }

    public UserRowMapper getUserRowMapper() {
        return userRowMapper;
    }

    public AccountRowMapper getAccountRowMapper() {
        return accountRowMapper;
    }

    public DepositRowMapper getDepositRowMapper() {
        return depositRowMapper;
    }

    public CreditRowMapper getCreditRowMapper() {
        return creditRowMapper;
    }
}
