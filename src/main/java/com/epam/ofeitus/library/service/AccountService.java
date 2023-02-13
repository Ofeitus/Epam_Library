package com.epam.ofeitus.library.service;

import com.epam.ofeitus.library.entity.bank.Account;
import com.epam.ofeitus.library.service.exception.ServiceException;

import java.util.List;

public interface AccountService {
    List<Account> getAllAccounts(int page, int itemsOnPage) throws ServiceException;

    int countAllAccounts() throws ServiceException;
}
