package com.epam.ofeitus.library.dao.impl;

import com.epam.ofeitus.library.dao.FinePaymentDao;
import com.epam.ofeitus.library.entity.user.FinePayment;

import java.util.List;

public class MySqlFinePaymentDao extends AbstractMySqlDao<FinePayment> implements FinePaymentDao {
    @Override
    public int save(FinePayment entity) {
        // TODO
        return 0;
    }

    @Override
    public int update(FinePayment entity) {
        // TODO
        return 0;
    }

    @Override
    public int deleteById(int id) {
        // TODO
        return 0;
    }

    @Override
    public List<FinePayment> findByUserId(int userId) {
        // TODO
        return null;
    }
}
