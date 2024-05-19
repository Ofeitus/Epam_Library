package com.epam.ofeitus.library.dao;

import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.entity.User;

import java.util.Date;
import java.util.List;

/**
 * User dao interface.
 */
public interface UserDao extends AbstractDao<User> {

    User findExistingById(int userId) throws DaoException;

    List<User> findAll(int page, int itemsOnPage) throws DaoException;

    int countAll() throws DaoException;

    User findByEmail(String email) throws DaoException;

    List<User> findByRoleId(int roleId, int offset, int itemsOnPage) throws DaoException;

    int countByRoleId(int roleId) throws DaoException;

    List<User> findBySearchRequest(int userRoleId, int userId, String email, int offset, int itemsOnPage) throws DaoException;

    int countBySearchRequest(int userRoleId, int userId, String email, Date date) throws DaoException;

    int restoreById(int userId) throws DaoException;
}
