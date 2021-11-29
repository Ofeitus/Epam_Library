package com.epam.ofeitus.library.dao;

import com.epam.ofeitus.library.dao.exception.DaoException;

import java.util.List;

public interface AbstractDao<T> {
    int save(T entity);

    T findById(int id) throws DaoException;

    List<T> findAll() throws DaoException;

    int update(T entity);

    int deleteById(int id) throws DaoException;
}
