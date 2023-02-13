package com.epam.ofeitus.library.service.impl;

import com.epam.ofeitus.library.dao.CreditDao;
import com.epam.ofeitus.library.dao.DepositDao;
import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.dao.factory.impl.MySqlDaoFactory;
import com.epam.ofeitus.library.entity.bank.Credit;
import com.epam.ofeitus.library.entity.bank.Currency;
import com.epam.ofeitus.library.entity.user.User;
import com.epam.ofeitus.library.service.CreditService;
import com.epam.ofeitus.library.service.exception.ServiceException;

import java.math.BigDecimal;
import java.util.Date;

public class CreditServiceImpl implements CreditService {
    @Override
    public boolean addCredit(User user, int creditType, int number, Date startDate, Date endDate, int term, Currency currency, BigDecimal amount, BigDecimal percent) throws ServiceException {
        CreditDao creditDao = MySqlDaoFactory.getInstance().getCreditDao();

        try {
            Credit credit = new Credit(0, creditType, number, currency, startDate, endDate, term, amount, percent, null, null);

            return creditDao.save(credit, user) == 1;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
