package com.epam.ofeitus.library.dao;

import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.entity.bank.Credit;
import com.epam.ofeitus.library.entity.user.User;

public interface CreditDao extends AbstractDao<Credit> {
    int save(Credit entity, User user) throws DaoException;
}
