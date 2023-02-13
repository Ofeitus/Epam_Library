package com.epam.ofeitus.library.service.impl;

import com.epam.ofeitus.library.dao.AccountDao;
import com.epam.ofeitus.library.dao.DepositDao;
import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.dao.factory.impl.MySqlDaoFactory;
import com.epam.ofeitus.library.entity.bank.*;
import com.epam.ofeitus.library.entity.user.User;
import com.epam.ofeitus.library.service.DepositService;
import com.epam.ofeitus.library.service.exception.ServiceException;

import java.math.BigDecimal;
import java.util.Date;

public class DepositServiceImpl implements DepositService {
    @Override
    public boolean addDeposit(User user, int depositType, int number, Date startDate, Date endDate, int term, Currency currency, BigDecimal amount, BigDecimal percent) throws ServiceException {
        DepositDao depositDao = MySqlDaoFactory.getInstance().getDepositDao();

        try {
            Deposit deposit = new Deposit(0, depositType, number, currency, startDate, endDate, term, amount, percent, null, null);

            return depositDao.save(deposit, user) == 1;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
