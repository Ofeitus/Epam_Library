package com.epam.ofeitus.library.dao.impl;

import com.epam.ofeitus.library.constant.Column;
import com.epam.ofeitus.library.constant.Table;
import com.epam.ofeitus.library.dao.BookCategoryDao;
import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.dao.rowmapper.RowMapperFactory;
import com.epam.ofeitus.library.entity.book.constituent.BookCategory;

public class MySqlBookCategoryDao extends AbstractMySqlDao<BookCategory> implements BookCategoryDao {
    public static final String SAVE_CATEGORY_QUERY = String.format(
            "INSERT INTO %s (%s, %s) VALUES (0, ?)",
            Table.BOOK_CATEGORY_TABLE,
            Column.CATEGORY_ID,
            Column.CATEGORY_NAME);
    public static final String UPDATE_CATEGORY_QUERY = String.format(
            "UPDATE %s SET %s=? WHERE %s=?",
            Table.BOOK_CATEGORY_TABLE,
            Column.CATEGORY_NAME,
            Column.CATEGORY_ID);
    private static final String FIND_BY_NAME_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=?",
            Table.BOOK_CATEGORY_TABLE,
            Column.CATEGORY_NAME);

    public MySqlBookCategoryDao() {
        super(RowMapperFactory.getInstance().getBookCategoryRowMapper(), Table.BOOK_CATEGORY_TABLE, Column.BOOK_CATEGORY_ID);
    }

    @Override
    public int save(BookCategory entity) throws DaoException {
        return queryOperator.executeUpdate(
                SAVE_CATEGORY_QUERY,
                entity.getName());
    }

    @Override
    public int update(BookCategory entity) throws DaoException {
        return queryOperator.executeUpdate(
                UPDATE_CATEGORY_QUERY,
                entity.getName(),
                entity.getCategoryId());
    }

    @Override
    public BookCategory findByName(String name) throws DaoException {
        return queryOperator.executeSingleEntityQuery(FIND_BY_NAME_QUERY, name);
    }
}
