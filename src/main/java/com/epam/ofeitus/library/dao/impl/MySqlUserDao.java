package com.epam.ofeitus.library.dao.impl;

import com.epam.ofeitus.library.constant.Column;
import com.epam.ofeitus.library.constant.Table;
import com.epam.ofeitus.library.dao.UserDao;
import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.dao.rowmapper.RowMapperFactory;
import com.epam.ofeitus.library.entity.user.User;
import com.epam.ofeitus.library.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MySqlUserDao extends AbstractMySqlDao<User> implements UserDao {
    public static final String SAVE_USER_QUERY = String.format(
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
    public static final String UPDATE_USER_QUERY = String.format(
            "UPDATE %s SET %s=?, %s=?, %s=?, %s=?, %s=?, %s=? WHERE %s=?",
            Table.USER_TABLE,
            Column.USER_NAME,
            Column.USER_SURNAME,
            Column.USER_PHONE_NUMBER,
            Column.USER_EMAIL,
            Column.USER_PASSWORD_HASH,
            Column.USER_ROLE_ID,
            Column.USER_ID);
    private static final String FIND_EXISTING_BY_ID_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=false AND %s=?",
            Table.USER_TABLE,
            Column.USER_DELETED,
            Column.USER_ID);
    private static final String FIND_BY_EMAIL_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=?",
            Table.USER_TABLE,
            Column.USER_EMAIL);
    private static final String FIND_BY_ROLE_ID_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=false AND %s=? LIMIT ?, ?",
            Table.USER_TABLE,
            Column.USER_DELETED,
            Column.USER_ROLE_ID);
    private static final String COUNT_BY_ROLE_ID_QUERY = String.format(
            "SELECT COUNT(*) FROM %s WHERE %s=false AND %s=?",
            Table.USER_TABLE,
            Column.USER_DELETED,
            Column.USER_ROLE_ID);
    private static final String FIND_ALL_USERS_QUERY = String.format(
            "SELECT * FROM %s JOIN %s UserRole ON %s.%s = UserRole.%s LIMIT ?, ?",
            Table.USER_TABLE,
            Table.USER_ROLE_TABLE,
            Table.USER_TABLE,
            Column.USER_ROLE_ID,
            Column.USER_ROLE_ID);
    private static final String COUNT_ALL_QUERY = String.format(
            "SELECT COUNT(*) FROM %s",
            Table.USER_TABLE);
    private static final String DELETE_USER_QUERY = String.format(
            "UPDATE %s SET %s=true WHERE %s=?",
            Table.USER_TABLE,
            Column.USER_DELETED,
            Column.USER_ID);
    private static final String RESTORE_USER_QUERY = String.format(
            "UPDATE %s SET %s=false WHERE %s=?",
            Table.USER_TABLE,
            Column.USER_DELETED,
            Column.USER_ID);

    public MySqlUserDao() {
        super(RowMapperFactory.getInstance().getUserRowMapper(), Table.USER_TABLE, Column.USER_ID);
    }

    @Override
    public List<User> findAll(int offset, int itemsOnPage) throws DaoException {
        return queryOperator.executeQuery(FIND_ALL_USERS_QUERY, offset, itemsOnPage);
    }

    @Override
    public User findById(int userId) throws DaoException {
        return queryOperator.executeSingleEntityQuery(FIND_BY_ID_QUERY, userId);
    }

    @Override
    public User findExistingById(int userId) throws DaoException {
        return queryOperator.executeSingleEntityQuery(FIND_EXISTING_BY_ID_QUERY, userId);
    }

    @Override
    public int countAll() throws DaoException {
        return queryOperator.executeCountQuery(COUNT_ALL_QUERY);
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
    public List<User> findByRoleId(int roleId, int offset, int itemsOnPage) throws DaoException {
        return queryOperator.executeQuery(FIND_BY_ROLE_ID_QUERY, roleId, offset, itemsOnPage);
    }

    @Override
    public int countByRoleId(int roleId) throws DaoException {
        return queryOperator.executeCountQuery(COUNT_BY_ROLE_ID_QUERY, roleId);
    }

    @Override
    public List<User> findBySearchRequest(int userRoleId, int userId, String email, int offset, int itemsOnPage) throws DaoException {
        List<Object> parameters = new ArrayList<>();

        String FIND_BY_SEARCH_REQUEST_QUERY = String.format(
                "SELECT * FROM %s ",
                Table.USER_TABLE);

        if (!email.equals("")) {
            FIND_BY_SEARCH_REQUEST_QUERY += String.format(
                    "WHERE %s=? ",
                    Column.USER_EMAIL);
            parameters.add(email);
        } else {
            FIND_BY_SEARCH_REQUEST_QUERY += "WHERE 1=1 ";
        }

        if (userId != 0) {
            FIND_BY_SEARCH_REQUEST_QUERY += String.format(
                    "AND %s=? ",
                    Column.USER_ID);
            parameters.add(userId);
        }

        if (userRoleId != 0) {
            FIND_BY_SEARCH_REQUEST_QUERY += String.format(
                    "AND %s=? ",
                    Column.USER_ROLE_ID);
            parameters.add(userRoleId);
        }

        FIND_BY_SEARCH_REQUEST_QUERY += String.format(
                "ORDER BY %s ",
                Column.USER_ID
        );

        FIND_BY_SEARCH_REQUEST_QUERY += "LIMIT ?, ?";
        parameters.add(offset);
        parameters.add(itemsOnPage);

        return queryOperator.executeQuery(
                FIND_BY_SEARCH_REQUEST_QUERY,
                parameters.toArray()
        );
    }

    @Override
    public int countBySearchRequest(int userRoleId, int userId, String email, Date date) throws DaoException {
        List<Object> parameters = new ArrayList<>();

        String FIND_BY_SEARCH_REQUEST_QUERY = String.format(
                "SELECT COUNT(*) FROM %s WHERE %s <= ? ",
                Table.USER_TABLE,
                Column.USER_REGISTRATION_DATE);
        parameters.add(date);

        if (!email.equals("")) {
            FIND_BY_SEARCH_REQUEST_QUERY += String.format(
                    "AND %s=? ",
                    Column.USER_EMAIL);
            parameters.add(email);
        }

        if (userId != 0) {
            FIND_BY_SEARCH_REQUEST_QUERY += String.format(
                    "AND %s=? ",
                    Column.USER_ID);
            parameters.add(userId);
        }

        if (userRoleId != 0) {
            FIND_BY_SEARCH_REQUEST_QUERY += String.format(
                    "AND %s=? ",
                    Column.USER_ROLE_ID);
            parameters.add(userRoleId);
        }

        FIND_BY_SEARCH_REQUEST_QUERY += String.format(
                "ORDER BY %s",
                Column.USER_ID
        );

        return queryOperator.executeCountQuery(
                FIND_BY_SEARCH_REQUEST_QUERY,
                parameters.toArray()
        );
    }

    @Override
    public int restoreById(int userId) throws DaoException {
        return queryOperator.executeUpdate(RESTORE_USER_QUERY, userId);
    }

    /**
     * Soft delete user (set delete flag in data source).
     *
     * @param userId user id
     * @throws DaoException thrown when dao exception occurs
     */
    @Override
    public int deleteById(int userId) throws DaoException {
        return queryOperator.executeUpdate(DELETE_USER_QUERY, userId);
    }
}
