package com.epam.ofeitus.library.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Row mapper interface.
 *
 * @param <T> entity type
 */
public interface RowMapper<T> {
    /**
     * Builds entity from result set
     *
     * @param resultSet query result set
     * @return entity type
     * @throws SQLException sql exception
     */
    T map(ResultSet resultSet) throws SQLException;
}
