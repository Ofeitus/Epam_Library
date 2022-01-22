package com.epam.ofeitus.library.service;

import com.epam.ofeitus.library.entity.report.UserCompositionReport;
import com.epam.ofeitus.library.entity.user.User;
import com.epam.ofeitus.library.service.exception.ServiceException;

import java.util.Date;
import java.util.List;

public interface UserService {
    boolean register(String firstName, String lastName, String email, String password) throws ServiceException;

    User login(String email, String password) throws ServiceException;

    boolean editPersonalData(int userId, String name, String surname, String phoneNumber) throws ServiceException;

    void deleteUser(int userId) throws ServiceException;

    void restoreUser(int userId) throws ServiceException;

    void setRole(int userId, int roleId) throws ServiceException;

    User getByUserId(int userId) throws ServiceException;

    User getByEmail(String email) throws ServiceException;

    List<User> getAll(int page, int itemsOnPage) throws ServiceException;

    int countAll() throws ServiceException;

    List<User> getAllMembers(int page, int itemsOnPage) throws ServiceException;

    int countAllMembers() throws ServiceException;

    List<User> getUsersBySearchRequest(int userRoleId, int userId, String email, int page, int itemsOnPage) throws ServiceException;

    int countUsersBySearchRequest(int userRoleId, int userId, String email) throws ServiceException;

    UserCompositionReport getUserCompositionReport(Date fromDate, Date toDate) throws ServiceException;
}
