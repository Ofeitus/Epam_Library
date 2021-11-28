package com.epam.ofeitus.library.dao.queryoperator.impl;

import com.epam.ofeitus.library.dao.queryoperator.QueryOperator;
import com.epam.ofeitus.library.dao.rowmapper.RowMapper;

import java.util.List;

public class MySqlQueryOperator<T> implements QueryOperator<T> {
    public MySqlQueryOperator(RowMapper<T> mapper) {
    }

    @Override
    public List<T> executeQuery(String query) {
        // TODO
        return null;
    }

    @Override
    public T executeSingleEntityQuery(String query) {
        // TODO
        return null;
    }

    @Override
    public int executeUpdate(String query) {
        // TODO
        return 0;
    }
}
