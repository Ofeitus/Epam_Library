package com.epam.ofeitus.library.dao.queryoperator.impl;

import com.epam.ofeitus.library.dao.connectionpool.ConnectionPoolException;
import com.epam.ofeitus.library.dao.connectionpool.ConnectionPool;
import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.dao.queryoperator.ParametrizedQuery;
import com.epam.ofeitus.library.dao.queryoperator.QueryOperator;
import com.epam.ofeitus.library.dao.rowmapper.RowMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlQueryOperator<T> implements QueryOperator<T> {
    Logger logger = LogManager.getLogger(MySqlQueryOperator.class);
    private final RowMapper<T> mapper;

    public MySqlQueryOperator(RowMapper<T> mapper) {
        this.mapper = mapper;
    }

    @Override
    public void setStatementParams(PreparedStatement statement, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i + 1, params[i]);
        }
    }

    @Override
    public List<T> executeQuery(String query, Object... params) throws DaoException {
        List<T> result = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            setStatementParams(statement, params);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                T entity = mapper.map(resultSet);
                result.add(entity);
            }
        } catch (SQLException e) {
            logger.error("Unable to execute select query.", e);
            throw new DaoException("Unable to execute select query.", e);
        } catch (ConnectionPoolException e) {
            logger.error("Unable to get connection.", e);
            throw new DaoException("Unable to get connection.", e);
        }
        return result;
    }

    @Override
    public int executeCountQuery(String query, Object... params) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            setStatementParams(statement, params);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt("COUNT(*)");
        } catch (SQLException e) {
            logger.error("Unable to execute select query.", e);
            throw new DaoException("Unable to execute select query.", e);
        } catch (ConnectionPoolException e) {
            logger.error("Unable to get connection.", e);
            throw new DaoException("Unable to get connection.", e);
        }
    }

    @Override
    public T executeSingleEntityQuery(String query, Object... params) throws DaoException {
        List<T> result = executeQuery(query, params);
        if (result.size() > 0) {
            return result.get(0);
        } else {
            return null;
        }
    }

    @Override
    public int executeUpdate(String query, Object... params) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            setStatementParams(statement, params);
            int rowsAffected = statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys != null && generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                return rowsAffected;
            }
        } catch (SQLException e) {
            logger.error("Unable to execute update query.", e);
            throw new DaoException("Unable to execute update query.", e);
        } catch (ConnectionPoolException e) {
            logger.error("Unable to get connection.", e);
            throw new DaoException("Unable to get connection.", e);
        }
    }

    private void rollbackTransaction(Connection connection) throws DaoException {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                logger.error("Unable to rollback transaction", e);
                throw new DaoException("Unable to rollback transaction", e);
            }
        }
    }

    private void releaseConnection(Connection connection) throws DaoException {
        if (connection != null) {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                logger.error("Unable to return connection to connection pool.", e);
                throw new DaoException("Unable to return connection to connection pool.", e);
            }
        }
    }

    @Override
    public int executeTransaction(List<ParametrizedQuery> queries) throws DaoException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            connection.setAutoCommit(false);
            int firstQueryGeneratedKey = -1;
            boolean idSet = false;
            for (ParametrizedQuery query : queries) {
                PreparedStatement statement = connection.prepareStatement(query.getQuery(), Statement.RETURN_GENERATED_KEYS);
                setStatementParams(statement, query.getParams());
                statement.executeUpdate();
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (!idSet && generatedKeys != null && generatedKeys.next()) {
                    firstQueryGeneratedKey = generatedKeys.getInt(1);
                    idSet = true;
                }
            }
            connection.commit();
            return firstQueryGeneratedKey;
        } catch (SQLException e) {
            logger.error("Unable to execute update query.", e);
            rollbackTransaction(connection);
            throw new DaoException("Unable to execute update query.", e);
        } catch (ConnectionPoolException e) {
            logger.error("Unable to take connection.", e);
            throw new DaoException("Unable to take connection.", e);
        } finally {
            releaseConnection(connection);
        }
    }
}
