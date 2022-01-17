package com.epam.ofeitus.library.dao;

import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.entity.user.User;

import java.util.List;

public interface UserDao extends AbstractDao<User> {
    List<User> findAll(int page, int itemsOnPage) throws DaoException;

    int countAll() throws DaoException;

    User findByEmail(String email) throws DaoException;

    List<User> findByRoleId(int roleId, int offset, int itemsOnPage) throws DaoException;

    int countByRoleId(int roleId) throws DaoException;

    List<User> findBySearchRequest(int userRoleId, int userId, String email, int offset, int itemsOnPage) throws DaoException;

    int countBySearchRequest(int userRoleId, int userId, String email) throws DaoException;
}
