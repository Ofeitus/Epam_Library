package com.epam.ofeitus.library.dao.queryoperator;

import com.epam.ofeitus.library.dao.exception.DaoException;

import java.util.List;

public interface QueryOperator<T> {
    List<T> executeQuery(String query) throws DaoException;

    T executeSingleEntityQuery(String query) throws DaoException;

    int executeUpdate(String query) throws DaoException;
}
