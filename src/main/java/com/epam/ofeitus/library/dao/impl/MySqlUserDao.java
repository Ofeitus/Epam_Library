package com.epam.ofeitus.library.dao.impl;

import com.epam.ofeitus.library.dao.UserDao;
import com.epam.ofeitus.library.entity.user.User;

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
}
