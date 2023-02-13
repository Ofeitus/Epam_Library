package com.epam.ofeitus.library.service;

import com.epam.ofeitus.library.entity.bank.Currency;
import com.epam.ofeitus.library.entity.user.User;
import com.epam.ofeitus.library.service.exception.ServiceException;

import java.math.BigDecimal;
import java.util.Date;

public interface DepositService {
    boolean addDeposit(User user, int depositType, int number, Date startDate, Date endDate, int term, Currency currency, BigDecimal amount, BigDecimal percent) throws ServiceException;
}
