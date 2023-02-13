package com.epam.ofeitus.library.dao.rowmapper.impl;

import com.epam.ofeitus.library.dao.rowmapper.RowMapper;
import com.epam.ofeitus.library.entity.bank.Credit;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CreditRowMapper implements RowMapper<Credit> {
    @Override
    public Credit map(ResultSet resultSet) throws SQLException {
        return null;
    }
}
