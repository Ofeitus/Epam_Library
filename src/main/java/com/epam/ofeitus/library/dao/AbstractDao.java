package com.epam.ofeitus.library.dao;

import java.util.List;

public interface AbstractDao<T> {
    int save(T entity);

    T findById(int id);

    List<T> findAll();

    int update(T entity);

    int deleteById(int id);
}
