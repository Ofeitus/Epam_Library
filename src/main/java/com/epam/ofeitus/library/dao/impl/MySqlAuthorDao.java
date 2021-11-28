package com.epam.ofeitus.library.dao.impl;

import com.epam.ofeitus.library.constant.Column;
import com.epam.ofeitus.library.constant.Table;
import com.epam.ofeitus.library.dao.AuthorDao;
import com.epam.ofeitus.library.dao.rowmapper.RowMapperFactory;
import com.epam.ofeitus.library.entity.book.Author;

public class MySqlAuthorDao extends AbstractMySqlDao<Author> implements AuthorDao {
    public MySqlAuthorDao() {
        super(RowMapperFactory.getAuthorRowMapper(), Table.AUTHOR_TABLE, Column.AUTHOR_ID);
    }

    @Override
    public int save(Author entity) {
        // TODO
        return 0;
    }

    @Override
    public int update(Author entity) {
        // TODO
        return 0;
    }

    @Override
    public Author findByName(String name, String surName) {
        // TODO
        return null;
    }
}
