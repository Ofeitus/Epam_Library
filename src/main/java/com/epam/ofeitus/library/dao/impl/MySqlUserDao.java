package com.epam.ofeitus.library.dao.impl;

import com.epam.ofeitus.library.constant.Column;
import com.epam.ofeitus.library.constant.Table;
import com.epam.ofeitus.library.dao.UserDao;
import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.dao.rowmapper.RowMapperFactory;
import com.epam.ofeitus.library.entity.user.User;

import java.util.List;

public class MySqlUserDao extends AbstractMySqlDao<User> implements UserDao {
    public final static String SAVE_USER_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s, %s) VALUES (0, ?, ?, ?, ?, ?, ?, 0)",
            Table.USER_TABLE,
            Column.USER_ID,
            Column.USER_NAME,
            Column.USER_SURNAME,
            Column.USER_PHONE_NUMBER,
            Column.USER_EMAIL,
            Column.USER_PASSWORD_HASH,
            Column.USER_ROLE_ID,
            Column.USER_DELETED);
    public final static String UPDATE_USER_QUERY = String.format(
            "UPDATE %s SET %s=?, %s=?, %s=?, %s=?, %s=?, %s=? WHERE %s=?",
            Table.USER_TABLE,
            Column.USER_NAME,
            Column.USER_SURNAME,
            Column.USER_PHONE_NUMBER,
            Column.USER_EMAIL,
            Column.USER_PASSWORD_HASH,
            Column.USER_ROLE_ID,
            Column.USER_ID);
    private static final String FIND_BY_EMAIL_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=?",
            Table.USER_TABLE,
            Column.USER_EMAIL);
    private static final String FIND_BY_ROLE_ID_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=?",
            Table.USER_TABLE,
            Column.USER_ROLE_ID);
    private static final String FIND_ALL_QUERY = String.format(
            "SELECT * FROM %s JOIN %s UserRole ON %s.%s = UserRole.%s WHERE %s=false",
            Table.USER_TABLE,
            Table.USER_ROLE_TABLE,
            Table.USER_TABLE,
            Column.USER_ROLE_ID,
            Column.ROLE_ID,
            Column.USER_DELETED);
    private static final String DELETE_USER_QUERY = String.format(
            "UPDATE %s SET %s=true WHERE %s=?",
            Table.USER_TABLE,
            Column.USER_DELETED,
            Column.USER_ID);

    public MySqlUserDao() {
        super(RowMapperFactory.getUserRowMapper(), Table.USER_TABLE, Column.USER_ID);
    }

    @Override
    public List<User> findAll() throws DaoException {
        return queryOperator.executeQuery(FIND_ALL_QUERY);
    }

    @Override
    public int save(User entity) throws DaoException {
        return queryOperator.executeUpdate(
                SAVE_USER_QUERY,
                entity.getName(),
                entity.getSurname(),
                entity.getEmail(),
                entity.getPhoneNumber(),
                entity.getPasswordHash(),
                entity.getUserRole().ordinal() + 1
        );
    }

    @Override
    public int update(User entity) throws DaoException {
        return queryOperator.executeUpdate(
                UPDATE_USER_QUERY,
                entity.getName(),
                entity.getSurname(),
                entity.getPhoneNumber(),
                entity.getEmail(),
                entity.getPasswordHash(),
                entity.getUserRole().ordinal() + 1,
                entity.getUserId());
    }

    @Override
    public User findByEmail(String email) throws DaoException {
        return queryOperator.executeSingleEntityQuery(FIND_BY_EMAIL_QUERY, email);
    }

    @Override
    public List<User> findByRoleId(int roleId) throws DaoException {
        return queryOperator.executeQuery(FIND_BY_ROLE_ID_QUERY, roleId);
    }

    @Override
    public int deleteById(int id) throws DaoException {
        return queryOperator.executeUpdate(DELETE_USER_QUERY, id);
    }
}
