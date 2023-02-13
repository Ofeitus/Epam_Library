package com.epam.ofeitus.library.dao;

import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.entity.user.User;

import java.util.List;

/**
 * Abstract dao interface.
 *
 * @param <T> entity type
 */
public interface AbstractDao<T> {
    /**
     * Save entity to data source.
     *
     * @param entity entity
     * @param user
     * @return result of data source query
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    int save(T entity) throws DaoException;

    /**
     * Update entity.
     *
     * @param entity entity to update
     * @return result of data source query
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    int update(T entity) throws DaoException;

    /**
     * Delete entity by id.
     *
     * @param id entity id
     * @return result of data source query
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    int deleteById(int id) throws DaoException;

    /**
     * Find entity by id.
     *
     * @param id entity id
     * @return found entity
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    T findById(int id) throws DaoException;

    /**
     * Find all.
     *
     * @return list of entities
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    List<T> findAll() throws DaoException;
}
