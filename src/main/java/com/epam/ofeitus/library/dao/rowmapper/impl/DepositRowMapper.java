package com.epam.ofeitus.library.dao.rowmapper.impl;

import com.epam.ofeitus.library.dao.rowmapper.RowMapper;
import com.epam.ofeitus.library.entity.bank.Deposit;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepositRowMapper implements RowMapper<Deposit> {
    @Override
    public Deposit map(ResultSet resultSet) throws SQLException {
        return null;
    }
}
