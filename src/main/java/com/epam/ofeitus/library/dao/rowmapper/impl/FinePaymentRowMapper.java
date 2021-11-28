package com.epam.ofeitus.library.dao.rowmapper.impl;

import com.epam.ofeitus.library.dao.rowmapper.Column;
import com.epam.ofeitus.library.dao.rowmapper.RowMapper;
import com.epam.ofeitus.library.entity.user.FinePayment;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FinePaymentRowMapper implements RowMapper<FinePayment> {
    @Override
    public FinePayment map(ResultSet resultSet) throws SQLException {
        FinePayment finePayment = new FinePayment();
        finePayment.setPaymentId(resultSet.getInt(Column.FINE_PAYMENT_ID));
        finePayment.setUserId(resultSet.getInt(Column.FINE_USER_ID));
        finePayment.setDate(resultSet.getDate(Column.FINE_DATE));
        finePayment.setAmount(resultSet.getBigDecimal(Column.FINE_AMOUNT));
        return finePayment;
    }
}
