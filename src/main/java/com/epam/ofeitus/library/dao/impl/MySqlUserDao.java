package com.epam.ofeitus.library.dao.impl;

import com.epam.ofeitus.library.constant.Column;
import com.epam.ofeitus.library.constant.Table;
import com.epam.ofeitus.library.dao.UserDao;
import com.epam.ofeitus.library.dao.rowmapper.RowMapperFactory;
import com.epam.ofeitus.library.entity.user.User;
import com.epam.ofeitus.library.entity.user.constituents.UserRole;

import java.util.List;

public class MySqlUserDao extends AbstractMySqlDao<User> implements UserDao {
    public MySqlUserDao() {
        super(RowMapperFactory.getUserRowMapper(), Table.USER_TABLE, Column.USER_ID);
    }

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
