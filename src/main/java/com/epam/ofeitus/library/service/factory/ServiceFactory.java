package com.epam.ofeitus.library.service.factory;

import com.epam.ofeitus.library.service.*;
import com.epam.ofeitus.library.service.impl.*;

/**
 * Factory, that provides services.
 */
public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private static final AccountService accountService = new AccountServiceImpl();
    private static final DepositService depositService = new DepositServiceImpl();
    private static final CreditService creditService = new CreditServiceImpl();
    private static final UserService userService = new UserServiceImpl();
    private static final BookService bookService = new BookServiceImpl();
    private static final LoansService loanService = new LoanServiceImpl();
    private static final ReservationsService reservationsService = new ReservationsServiceImpl();

    private ServiceFactory() {
    }

    /**
     * Gets instance.
     *
     * @return instance of ServiceFactory
     */
    public static ServiceFactory getInstance() {
        return instance;
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public DepositService getDepositService() {
        return depositService;
    }

    public CreditService getCreditService() {
        return creditService;
    }

    public UserService getUserService() {
        return userService;
    }

    public BookService getBookService() {
        return bookService;
    }

    public LoansService getLoansService() {
        return loanService;
    }

    public ReservationsService getReservationsService() {
        return reservationsService;
    }
}
