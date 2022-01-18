package com.epam.ofeitus.library.service;

import com.epam.ofeitus.library.entity.report.UserCompositionReport;
import com.epam.ofeitus.library.entity.user.User;
import com.epam.ofeitus.library.service.exception.ServiceException;

import java.util.Date;
import java.util.List;

public interface UserService {
    User login(String email, String password) throws ServiceException;

    void register(String firstName, String lastName, String email, String password) throws ServiceException;

    List<User> getAll(int page, int itemsOnPage) throws ServiceException;

    int countAll() throws ServiceException;

    User getByEmail(String email) throws ServiceException;

    int editPersonalData(int id, String name, String surname, String phoneNumber) throws ServiceException;

    List<User> getAllMembers(int page, int itemsOnPage) throws ServiceException;

    int countAllMembers() throws ServiceException;

    User getByUserId(int userId) throws ServiceException;

    List<User> getUsersBySearchRequest(int userRoleId, int userId, String email, int page, int itemsOnPage) throws ServiceException;

    int countUsersBySearchRequest(int userRoleId, int userId, String email) throws ServiceException;

    int setRole(int userId, int roleId) throws ServiceException;

    int deleteUser(int userId) throws ServiceException;

    int restoreUser(int userId) throws ServiceException;

    UserCompositionReport getUserCompositionReport(Date fromDate, Date toDate) throws ServiceException;
}
