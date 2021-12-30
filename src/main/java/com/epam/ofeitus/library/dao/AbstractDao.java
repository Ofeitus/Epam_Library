package com.epam.ofeitus.library.dao;

import com.epam.ofeitus.library.dao.exception.DaoException;

import java.util.List;

public interface AbstractDao<T> {
    int save(T entity) throws DaoException;

    T findById(int id) throws DaoException;

    List<T> findAllExisting() throws DaoException;

    int update(T entity) throws DaoException;

    int deleteById(int id) throws DaoException;
}
