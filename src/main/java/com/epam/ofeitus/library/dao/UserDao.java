package com.epam.ofeitus.library.dao;

import com.epam.ofeitus.library.entity.user.User;
import com.epam.ofeitus.library.entity.user.constituents.UserRole;

import java.util.List;

public interface UserDao extends AbstractDao<User> {
    User findByEmail(String email);

    List<User> findByRole(UserRole role);
}
