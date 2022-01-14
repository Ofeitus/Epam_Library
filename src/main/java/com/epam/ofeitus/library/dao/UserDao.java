package com.epam.ofeitus.library.dao;

import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.entity.user.User;

import java.util.List;

public interface UserDao extends AbstractDao<User> {
    User findByEmail(String email) throws DaoException;

    List<User> findByRoleId(int roleId, int offset, int itemsOnPage) throws DaoException;

    int countByRoleId(int roleId) throws DaoException;

    List<User> findBySearchRequest(int userId, String email, int offset, int itemsOnPage) throws DaoException;

    int countBySearchRequest(int userId, String email) throws DaoException;
}
