package com.epam.ofeitus.library.dao.queryoperator.impl;

import com.epam.ofeitus.library.dao.connectionpool.ConnectionPoolException;
import com.epam.ofeitus.library.dao.connectionpool.ConnectionPool;
import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.dao.queryoperator.QueryOperator;
import com.epam.ofeitus.library.dao.rowmapper.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlQueryOperator<T> implements QueryOperator<T> {
    private final RowMapper<T> mapper;

    public MySqlQueryOperator(RowMapper<T> mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<T> executeQuery(String query) throws DaoException {
        List<T> result = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                T entity = mapper.map(resultSet);
                result.add(entity);
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to execute select query.", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Unable to get connection.", e);
        }
        return result;
    }

    @Override
    public T executeSingleEntityQuery(String query) throws DaoException {
        List<T> result = executeQuery(query);
        if (result.size() > 0) {
            return result.get(0);
        } else {
            return null;
        }
    }

    @Override
    public int executeUpdate(String query) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            int rowsAffected = statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys != null && generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                return rowsAffected;
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to execute update query.", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Unable to get connection.", e);
        }
    }
}
