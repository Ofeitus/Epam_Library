package com.epam.ofeitus.library.dao;

import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.entity.bank.Account;

import java.util.List;

public interface AccountDao extends AbstractDao<Account> {
    List<Account> findAll(int offset, int itemsOnPage) throws DaoException;

    int countAll() throws DaoException;

    List<Account> findByUserId(int userId) throws DaoException;
}
