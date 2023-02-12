package com.epam.ofeitus.library.dao;

import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.entity.user.User;

import java.util.Date;
import java.util.List;

/**
 * User dao interface.
 */
public interface UserDao extends AbstractDao<User> {
    /**
     * Find existing by userId.
     *
     * @param userId      userId
     * @return User
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    User findExistingById(int userId) throws DaoException;

    /**
     * Find all.
     *
     * @param page        page
     * @param itemsOnPage items on page
     * @return users list
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    List<User> findAll(int page, int itemsOnPage) throws DaoException;

    /**
     * Count all.
     *
     * @return users count
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    int countAll() throws DaoException;

    /**
     * Find by email.
     *
     * @param email email
     * @return user
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    User findByEmail(String email) throws DaoException;

    User findByPassportNumber(String passportNumber) throws DaoException;

    User findByPassportId(String passportId) throws DaoException;

    /**
     * Find by role id.
     *
     * @param roleId      role id
     * @param offset      offset
     * @param itemsOnPage items on page
     * @return users list
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    List<User> findByRoleId(int roleId, int offset, int itemsOnPage) throws DaoException;

    /**
     * Count by role id.
     *
     * @param roleId role id
     * @return users count
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    int countByRoleId(int roleId) throws DaoException;

    /**
     * Find by search request.
     *
     * @param userRoleId  user role id
     * @param userId      user id
     * @param email       email
     * @param offset      offset
     * @param itemsOnPage items on page
     * @return users list
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    List<User> findBySearchRequest(int userRoleId, int userId, String email, int offset, int itemsOnPage) throws DaoException;

    /**
     * Count by search request.
     *
     * @param userRoleId user role id
     * @param userId     user id
     * @param email      email
     * @param date       date
     * @return users count
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    int countBySearchRequest(int userRoleId, int userId, String email, Date date) throws DaoException;

    /**
     * Restore user (remove delete flag in data source).
     *
     * @param userId user id
     * @return result of data source query
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    int restoreById(int userId) throws DaoException;
}
