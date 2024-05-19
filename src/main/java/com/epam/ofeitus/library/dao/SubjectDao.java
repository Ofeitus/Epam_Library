package com.epam.ofeitus.library.dao;

import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.entity.Subject;

/**
 * Subject dao interface.
 */
public interface SubjectDao extends AbstractDao<Subject> {

    int save(Subject entity) throws DaoException;

    int update(Subject entity) throws DaoException;

    int deleteById(int id) throws DaoException;
}
