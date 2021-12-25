package com.epam.ofeitus.library.dao.rowmapper.impl;

import com.epam.ofeitus.library.constant.Column;
import com.epam.ofeitus.library.dao.rowmapper.RowMapper;
import com.epam.ofeitus.library.entity.user.User;
import com.epam.ofeitus.library.entity.user.constituent.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User map(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getInt(Column.USER_ID));
        user.setName(resultSet.getString(Column.USER_NAME));
        user.setSurname(resultSet.getString(Column.USER_SURNAME));
        user.setPhoneNumber(resultSet.getString(Column.USER_PHONE_NUMBER));
        user.setEmail(resultSet.getString(Column.USER_EMAIL));
        user.setPasswordHash(resultSet.getString(Column.USER_PASSWORD_HASH));
        user.setUserRole(UserRole.values()[resultSet.getInt(Column.USER_ROLE_ID) - 1]);
        return user;
    }
}
