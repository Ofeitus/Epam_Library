package com.epam.ofeitus.library.service;

import com.epam.ofeitus.library.entity.user.User;
import com.epam.ofeitus.library.service.exception.ServiceException;

import java.util.List;

public interface UserService {
    User login(String email, String password) throws ServiceException;

    void register(String firstName, String lastName, String email, String password) throws ServiceException;

    List<User> getAll() throws ServiceException;

    User getByEmail(String email) throws ServiceException;

    int editPersonalData(int id, String name, String surname, String phoneNumber) throws ServiceException;

    List<User> getAllMembers(int page, int itemsOnPage) throws ServiceException;

    int countAllMembers() throws ServiceException;

    User getByUserId(int userId) throws ServiceException;

    List<User> getMemberBySearchRequest(int userId, String email, int page, int itemsOnPage) throws ServiceException;

    int countMembersBySearchRequest(int userId, String email) throws ServiceException;
}
