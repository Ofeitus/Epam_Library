package com.epam.ofeitus.library.dao.impl;

import com.epam.ofeitus.library.constant.Column;
import com.epam.ofeitus.library.constant.Table;
import com.epam.ofeitus.library.dao.FinePaymentDao;
import com.epam.ofeitus.library.dao.rowmapper.RowMapperFactory;
import com.epam.ofeitus.library.entity.user.FinePayment;

import java.util.List;

public class MySqlFinePaymentDao extends AbstractMySqlDao<FinePayment> implements FinePaymentDao {
    public MySqlFinePaymentDao() {
        super(RowMapperFactory.getFinePaymentRowMapper(), Table.FINE_PAYMENT_TABLE, Column.FINE_PAYMENT_ID);
    }

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
    public List<FinePayment> findByUserId(int userId) {
        // TODO
        return null;
    }
}
