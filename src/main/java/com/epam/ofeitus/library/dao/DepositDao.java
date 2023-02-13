package com.epam.ofeitus.library.dao;

import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.entity.bank.Deposit;
import com.epam.ofeitus.library.entity.user.User;

public interface DepositDao extends AbstractDao<Deposit> {
    int save(Deposit entity, User user) throws DaoException;
}
