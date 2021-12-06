package com.epam.ofeitus.library.dao.impl;

import com.epam.ofeitus.library.constant.Column;
import com.epam.ofeitus.library.constant.Table;
import com.epam.ofeitus.library.dao.CopyOfBookDao;
import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.dao.rowmapper.RowMapperFactory;
import com.epam.ofeitus.library.entity.book.CopyOfBook;

import java.util.List;

public class MySqlCopyOfBookDao extends AbstractMySqlDao<CopyOfBook> implements CopyOfBookDao {
    public final static String SAVE_COPY_OF_BOOK_QUERY = String.format(
            "INSERT INTO %s (%s, %s) VALUES (?, ?)",
            Table.COPY_OF_BOOK_TABLE,
            Column.BOOK_INVENTORY_ID,
            Column.BOOK_ISBN);
    public final static String UPDATE_COPY_OF_BOOK_QUERY = String.format(
            "UPDATE %s SET %s=? WHERE %s=?",
            Table.COPY_OF_BOOK_TABLE,
            Column.BOOK_ISBN,
            Column.BOOK_INVENTORY_ID);
    private static final String FIND_BY_ISBN_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=?",
            Table.COPY_OF_BOOK_TABLE,
            Column.BOOK_ISBN);

    public MySqlCopyOfBookDao() {
        super(RowMapperFactory.getCopyOfBookRowMapper(), Table.COPY_OF_BOOK_TABLE, Column.BOOK_INVENTORY_ID);
    }

    @Override
    public int save(CopyOfBook entity) throws DaoException {
        return queryOperator.executeUpdate(
                SAVE_COPY_OF_BOOK_QUERY,
                entity.getInventoryId(),
                entity.getBookIsbn());
    }

    @Override
    public int update(CopyOfBook entity) throws DaoException {
        return queryOperator.executeUpdate(
                UPDATE_COPY_OF_BOOK_QUERY,
                entity.getBookIsbn(),
                entity.getInventoryId());
    }

    @Override
    public List<CopyOfBook> findByIsbn(String bookIsbn) throws DaoException {
        return queryOperator.executeQuery(FIND_BY_ISBN_QUERY, bookIsbn);
    }
}
