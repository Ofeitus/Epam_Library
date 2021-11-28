package com.epam.ofeitus.library.dao.rowmapper.impl;

import com.epam.ofeitus.library.dao.rowmapper.Column;
import com.epam.ofeitus.library.dao.rowmapper.RowMapper;
import com.epam.ofeitus.library.entity.user.User;
import com.epam.ofeitus.library.entity.user.constituents.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User map(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getInt(Column.USER_ID));
        user.setName(resultSet.getString(Column.USER_NAME));
        user.setSurName(resultSet.getString(Column.USER_SURNAME));
        user.setEmail(resultSet.getString(Column.USER_EMAIL));
        user.setPasswordHash(resultSet.getString(Column.USER_PASSWORD_HASH));
        user.setUserRole(UserRole.valueOf(resultSet.getString(Column.ROLE_NAME).toUpperCase()));
        return user;
    }
}
