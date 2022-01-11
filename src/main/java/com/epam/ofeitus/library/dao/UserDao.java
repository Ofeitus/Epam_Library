package com.epam.ofeitus.library.dao;

import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.entity.user.User;
import com.epam.ofeitus.library.entity.user.constituent.UserRole;

import java.util.List;

public interface UserDao extends AbstractDao<User> {
    User findByEmail(String email) throws DaoException;

    List<User> findByRoleId(int roleId) throws DaoException;

    List<User> findBySearchRequest(int userId, String email) throws DaoException;
}
