package com.epam.ofeitus.library.dao.impl;

import com.epam.ofeitus.library.dao.AbstractDao;
import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.dao.queryoperator.QueryOperator;
import com.epam.ofeitus.library.dao.queryoperator.impl.MySqlQueryOperator;
import com.epam.ofeitus.library.dao.rowmapper.RowMapper;

import java.util.List;

public abstract class AbstractMySqlDao<T> implements AbstractDao<T> {
    protected final String FIND_ALL_QUERY;
    protected final String FIND_BY_ID_QUERY;
    protected final String DELETE_BY_ID_QUERY;

    protected final RowMapper<T> mapper;
    protected final QueryOperator<T> queryOperator;

    protected AbstractMySqlDao(RowMapper<T> mapper, String tableName, String idName) {
        queryOperator = new MySqlQueryOperator<>(mapper);
        this.mapper = mapper;
        FIND_ALL_QUERY = "SELECT * FROM " + tableName;
        FIND_BY_ID_QUERY = "SELECT * FROM " + tableName + " WHERE " + idName + "=?";
        DELETE_BY_ID_QUERY = "DELETE FROM " + tableName + " WHERE " + idName + "=?";
    }

    @Override
    public T findById(int id) throws DaoException {
        return queryOperator.executeSingleEntityQuery(FIND_BY_ID_QUERY, id);
    }

    @Override
    public List<T> findAllExisting() throws DaoException {
        return queryOperator.executeQuery(FIND_ALL_QUERY);
    }

    @Override
    public int deleteById(int id) throws DaoException {
        return queryOperator.executeUpdate(DELETE_BY_ID_QUERY, id);
    }
}
