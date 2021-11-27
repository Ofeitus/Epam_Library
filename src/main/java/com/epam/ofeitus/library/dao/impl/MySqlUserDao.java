package com.epam.ofeitus.library.dao.impl;

import com.epam.ofeitus.library.dao.UserDao;
import com.epam.ofeitus.library.entity.user.User;
import com.epam.ofeitus.library.entity.user.constituents.UserRole;

import java.util.List;

public class MySqlUserDao extends AbstractMySqlDao<User> implements UserDao {
    @Override
    public int save(User entity) {
        // TODO
        return 0;
    }

    @Override
    public int update(User entity) {
        // TODO
        return 0;
    }

    @Override
    public int deleteById(int id) {
        // TODO
        return 0;
    }

    @Override
    public User findByEmail(String email) {
        //TODO
        return null;
    }

    @Override
    public List<User> findByRole(UserRole role) {
        //TODO
        return null;
    }
}
