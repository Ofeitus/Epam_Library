package com.epam.ofeitus.library.dao.impl;

import com.epam.ofeitus.library.dao.AbstractDao;
import com.epam.ofeitus.library.dao.queryoperator.QueryOperator;
import com.epam.ofeitus.library.dao.queryoperator.impl.MySqlQueryOperator;
import com.epam.ofeitus.library.dao.rowmapper.RowMapper;

import java.util.List;

public abstract class AbstractMySqlDao<T> implements AbstractDao<T> {
    private static final String SELECT_ALL_QUERY = "SELECT * FROM ";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM ";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM ";

    protected final RowMapper<T> mapper;
    protected final QueryOperator<T> queryOperator;
    private final String tableName;
    private final String idName;

    protected AbstractMySqlDao(RowMapper<T> mapper, String tableName, String idName) {
        queryOperator = new MySqlQueryOperator<>(mapper);
        this.mapper = mapper;
        this.tableName = tableName;
        this.idName = idName;
    }

    @Override
    public T findById(int id) {
        String query = FIND_BY_ID_QUERY + tableName + " WHERE " + idName + "=" + id;
        return queryOperator.executeSingleEntityQuery(query);
    }

    @Override
    public List<T> findAll() {
        String query = SELECT_ALL_QUERY + tableName;
        return queryOperator.executeQuery(query);
    }

    @Override
    public int deleteById(int id) {
        String query = DELETE_BY_ID_QUERY + tableName + " WHERE " + idName + "=" + id;
        return queryOperator.executeUpdate(query);
    }
}
