package com.epam.ofeitus.library.dao;

import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.entity.user.FinePayment;

import java.util.List;

public interface FinePaymentDao extends AbstractDao<FinePayment> {
    List<FinePayment> findByUserId(int userId) throws DaoException;
}
