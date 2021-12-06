package com.epam.ofeitus.library.dao.impl;

import com.epam.ofeitus.library.constant.Column;
import com.epam.ofeitus.library.constant.Table;
import com.epam.ofeitus.library.dao.AuthorDao;
import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.dao.rowmapper.RowMapperFactory;
import com.epam.ofeitus.library.entity.book.Author;

import java.util.List;

public class MySqlAuthorDao extends AbstractMySqlDao<Author> implements AuthorDao {
    public final static String SAVE_AUTHOR_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s) VALUES (?, ?, ?)",
            Table.AUTHOR_TABLE,
            Column.AUTHOR_ID,
            Column.AUTHOR_NAME,
            Column.AUTHOR_SURNAME);
    public final static String UPDATE_AUTHOR_QUERY = String.format(
            "UPDATE %s SET %s=?, %s=? WHERE %s=?",
            Table.AUTHOR_TABLE,
            Column.AUTHOR_NAME,
            Column.AUTHOR_SURNAME,
            Column.AUTHOR_ID);
    private static final String FIND_BY_NAME_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=? AND %s=?",
            Table.AUTHOR_TABLE,
            Column.AUTHOR_NAME,
            Column.AUTHOR_SURNAME);

    public MySqlAuthorDao() {
        super(RowMapperFactory.getAuthorRowMapper(), Table.AUTHOR_TABLE, Column.AUTHOR_ID);
    }

    @Override
    public int save(Author entity) throws DaoException {
        return queryOperator.executeUpdate(
                SAVE_AUTHOR_QUERY,
                entity.getAuthorId(),
                entity.getName(),
                entity.getSurName());
    }

    @Override
    public int update(Author entity) throws DaoException {
        return queryOperator.executeUpdate(
                UPDATE_AUTHOR_QUERY,
                entity.getName(),
                entity.getSurName(),
                entity.getAuthorId());
    }

    @Override
    public Author findByName(String name, String surName) throws DaoException {
        return queryOperator.executeSingleEntityQuery(FIND_BY_NAME_QUERY, name, surName);
    }
}
