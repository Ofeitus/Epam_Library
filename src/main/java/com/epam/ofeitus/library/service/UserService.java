package com.epam.ofeitus.library.service;

import com.epam.ofeitus.library.entity.report.UserCompositionReport;
import com.epam.ofeitus.library.entity.user.User;
import com.epam.ofeitus.library.service.exception.ServiceException;

import java.util.Date;
import java.util.List;

/**
 * User service interface.
 */
public interface UserService {
    /**
     * Register new user.
     *
     * @param firstName user first name
     * @param lastName  user last name
     * @param email     user email
     * @param password  user password
     * @return if user was successfully registered
     * @throws ServiceException thrown when dao exception occurs
     */
    boolean register(String firstName, String lastName, String email, String password) throws ServiceException;

    /**
     * Log in user.
     *
     * @param email    user email
     * @param password user password
     * @return user entity if successfully logged in, null if log in data is invalid
     * @throws ServiceException thrown when dao exception occurs
     */
    User login(String email, String password) throws ServiceException;

    /**
     * Edit personal data.
     *
     * @param userId      user id
     * @param name        user name
     * @param surname     user surname
     * @param phoneNumber user phone number
     * @return if user data was successfully edited
     * @throws ServiceException thrown when dao exception occurs
     */
    boolean editPersonalData(int userId, String name, String surname, String phoneNumber) throws ServiceException;

    /**
     * Soft delete user (set delete flag in data source).
     *
     * @param userId user id
     * @throws ServiceException thrown when dao exception occurs
     */
    void deleteUser(int userId) throws ServiceException;

    /**
     * Restore user (remove delete flag in data source).
     *
     * @param userId user id
     * @throws ServiceException thrown when dao exception occurs
     */
    void restoreUser(int userId) throws ServiceException;

    /**
     * Sets user role.
     *
     * @param userId user id
     * @param roleId role id
     * @throws ServiceException thrown when dao exception occurs
     */
    void setRole(int userId, int roleId) throws ServiceException;

    /**
     * Gets user by user id.
     *
     * @param userId user id
     * @return user
     * @throws ServiceException thrown when dao exception occurs
     */
    User getByUserId(int userId) throws ServiceException;

    /**
     * Gets user by email.
     *
     * @param email email
     * @return user
     * @throws ServiceException thrown when dao exception occurs
     */
    User getByEmail(String email) throws ServiceException;

    /**
     * Gets all users on given page.
     *
     * @param page        page
     * @param itemsOnPage items on page
     * @return list of users
     * @throws ServiceException thrown when dao exception occurs
     */
    List<User> getAll(int page, int itemsOnPage) throws ServiceException;

    /**
     * Gets count of all users.
     *
     * @return users count
     * @throws ServiceException thrown when dao exception occurs
     */
    int countAll() throws ServiceException;

    /**
     * Gets all members on given page.
     *
     * @param page        page
     * @param itemsOnPage items on page
     * @return list of members
     * @throws ServiceException thrown when dao exception occurs
     */
    List<User> getAllMembers(int page, int itemsOnPage) throws ServiceException;

    /**
     * Gets count of all members.
     *
     * @return members count
     * @throws ServiceException thrown when dao exception occurs
     */
    int countAllMembers() throws ServiceException;

    /**
     * Gets users matching the search request on given page.
     *
     * @param userRoleId  user role id
     * @param userId      user id
     * @param email       user email
     * @param page        page
     * @param itemsOnPage items on page
     * @return list of users
     * @throws ServiceException thrown when dao exception occurs
     */
    List<User> getUsersBySearchRequest(int userRoleId, int userId, String email, int page, int itemsOnPage) throws ServiceException;

    /**
     * Gets count of users by search request.
     *
     * @param userRoleId user role id
     * @param userId     user id
     * @param email      user email
     * @return users count
     * @throws ServiceException thrown when dao exception occurs
     */
    int countUsersBySearchRequest(int userRoleId, int userId, String email) throws ServiceException;

    /**
     * Gets user composition report.
     *
     * @param fromDate from date
     * @param toDate   to date
     * @return user composition report
     * @throws ServiceException thrown when dao exception occurs
     */
    UserCompositionReport getUserCompositionReport(Date fromDate, Date toDate) throws ServiceException;
}
