package com.epam.ofeitus.library.service.impl;

import com.epam.ofeitus.library.dao.AccountDao;
import com.epam.ofeitus.library.dao.UserDao;
import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.dao.factory.impl.MySqlDaoFactory;
import com.epam.ofeitus.library.entity.bank.Account;
import com.epam.ofeitus.library.service.AccountService;
import com.epam.ofeitus.library.service.exception.ServiceException;

import java.util.List;

public class AccountServiceImpl implements AccountService {
    @Override
    public List<Account> getAllAccounts(int page, int itemsOnPage) throws ServiceException {
        AccountDao accountDao = MySqlDaoFactory.getInstance().getAccountDao();

        try {
            int offset = (page - 1) * itemsOnPage;

            return accountDao.findAll(offset, itemsOnPage);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int countAllAccounts() throws ServiceException {
        AccountDao accountDao = MySqlDaoFactory.getInstance().getAccountDao();

        try {
            return accountDao.countAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
