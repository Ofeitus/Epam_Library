package com.epam.ofeitus.library.dao.impl;

import com.epam.ofeitus.library.dao.AbstractDao;

import java.util.List;

public abstract class AbstractMySqlDao<T> implements AbstractDao<T> {
    @Override
    public T findById(int id) {
        // TODO
        return null;
    }

    @Override
    public List<T> findAll() {
        // TODO
        return null;
    }
}