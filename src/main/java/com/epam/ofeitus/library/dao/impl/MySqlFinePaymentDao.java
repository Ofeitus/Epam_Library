package com.epam.ofeitus.library.dao.impl;

import com.epam.ofeitus.library.constant.Column;
import com.epam.ofeitus.library.constant.Table;
import com.epam.ofeitus.library.dao.FinePaymentDao;
import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.dao.rowmapper.RowMapperFactory;
import com.epam.ofeitus.library.entity.user.FinePayment;

import java.util.List;

public class MySqlFinePaymentDao extends AbstractMySqlDao<FinePayment> implements FinePaymentDao {
    public final static String SAVE_FINE_PAYMENT_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s, %s) VALUES (0, ?, ?, ?)",
            Table.FINE_PAYMENT_TABLE,
            Column.FINE_PAYMENT_ID,
            Column.FINE_USER_ID,
            Column.FINE_DATE,
            Column.FINE_AMOUNT);
    public final static String UPDATE_FINE_PAYMENT_QUERY = String.format(
            "UPDATE %s SET %s=?, %s=?, %s=? WHERE %s=?",
            Table.FINE_PAYMENT_TABLE,
            Column.FINE_USER_ID,
            Column.FINE_DATE,
            Column.FINE_AMOUNT,
            Column.FINE_PAYMENT_ID);
    private static final String FIND_BY_USER_ID_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=?",
            Table.FINE_PAYMENT_TABLE,
            Column.FINE_USER_ID);

    public MySqlFinePaymentDao() {
        super(RowMapperFactory.getFinePaymentRowMapper(), Table.FINE_PAYMENT_TABLE, Column.FINE_PAYMENT_ID);
    }

    @Override
    public int save(FinePayment entity) throws DaoException {
        return queryOperator.executeUpdate(
                SAVE_FINE_PAYMENT_QUERY,
                entity.getUserId(),
                entity.getDate(),
                entity.getAmount());
    }

    @Override
    public int update(FinePayment entity) throws DaoException {
        return queryOperator.executeUpdate(
                UPDATE_FINE_PAYMENT_QUERY,
                entity.getUserId(),
                entity.getDate(),
                entity.getAmount(),
                entity.getPaymentId());
    }

    @Override
    public List<FinePayment> findByUserId(int userId) throws DaoException {
        return queryOperator.executeQuery(FIND_BY_USER_ID_QUERY, userId);
    }
}
