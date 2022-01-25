package com.epam.ofeitus.library.dao.queryoperator;

import com.epam.ofeitus.library.dao.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Query operator interface. Provides methods to execute various types of database queries.
 *
 * @param <T> entity type
 */
public interface QueryOperator<T> {
    /**
     * Sets PreparedStatement params.
     *
     * @param statement PreparedStatement
     * @param params    params
     * @throws SQLException sql exception
     */
    void setStatementParams(PreparedStatement statement, Object... params) throws SQLException;

    /**
     * Executes select query.
     *
     * @param query  query
     * @param params params
     * @return list of entities
     * @throws DaoException thrown, when sql exception occurs
     */
    List<T> executeQuery(String query, Object... params) throws DaoException;

    /**
     * Executes count query.
     *
     * @param query  query
     * @param params params
     * @return rows count from select query
     * @throws DaoException thrown, when sql exception occurs
     */
    int executeCountQuery(String query, Object... params) throws DaoException;

    /**
     * Executes single entity select query.
     *
     * @param query  query
     * @param params params
     * @return entity
     * @throws DaoException thrown, when sql exception occurs
     */
    T executeSingleEntityQuery(String query, Object... params) throws DaoException;

    /**
     * Executes update query.
     *
     * @param query  query
     * @param params params
     * @return result of database query
     * @throws DaoException thrown, when sql exception occurs
     */
    int executeUpdate(String query, Object... params) throws DaoException;

    /**
     * Executes transactional update query.
     *
     * @param queries queries
     * @return result of first query from database transaction
     * @throws DaoException thrown, when sql exception occurs
     */
    int executeTransaction(List<ParametrizedQuery> queries) throws DaoException;
}
