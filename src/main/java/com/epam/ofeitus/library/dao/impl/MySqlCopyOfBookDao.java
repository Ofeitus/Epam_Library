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
            "INSERT INTO %s (%s, %s, %s) VALUES (0, ?, ?)",
            Table.COPY_OF_BOOK_TABLE,
            Column.COPY_OF_BOOK_INVENTORY_ID,
            Column.BOOK_ISBN,
            Column.COPY_OF_BOOK_STATUS_ID);
    public final static String UPDATE_COPY_OF_BOOK_QUERY = String.format(
            "UPDATE %s SET %s=?, %s=? WHERE %s=?",
            Table.COPY_OF_BOOK_TABLE,
            Column.BOOK_ISBN,
            Column.COPY_OF_BOOK_STATUS_ID,
            Column.COPY_OF_BOOK_INVENTORY_ID);
    private static final String FIND_ALL_WITH_BOOK_QUERY = String.format(
            "SELECT * FROM %s JOIN %s b on b.%s = %s.%s ORDER BY %s",
            Table.COPY_OF_BOOK_TABLE,
            Table.BOOK_TABLE,
            Column.BOOK_ISBN,
            Table.COPY_OF_BOOK_TABLE,
            Column.COPY_OF_BOOK_ISBN,
            Column.COPY_OF_BOOK_INVENTORY_ID);
    private static final String FIND_BY_ISBN_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=?",
            Table.COPY_OF_BOOK_TABLE,
            Column.BOOK_ISBN);
    private static final String FIND_BY_ISBN_AND_STATUS_ID_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=? AND %s=?",
            Table.COPY_OF_BOOK_TABLE,
            Column.BOOK_ISBN,
            Column.COPY_OF_BOOK_STATUS_ID);

    public MySqlCopyOfBookDao() {
        super(RowMapperFactory.getCopyOfBookRowMapper(), Table.COPY_OF_BOOK_TABLE, Column.COPY_OF_BOOK_INVENTORY_ID);
    }

    @Override
    public int save(CopyOfBook entity) throws DaoException {
        return queryOperator.executeUpdate(
                SAVE_COPY_OF_BOOK_QUERY,
                entity.getBookIsbn(),
                entity.getCopyOfBookStatus().ordinal() + 1);
    }

    @Override
    public int update(CopyOfBook entity) throws DaoException {
        return queryOperator.executeUpdate(
                UPDATE_COPY_OF_BOOK_QUERY,
                entity.getBookIsbn(),
                entity.getCopyOfBookStatus().ordinal() + 1,
                entity.getInventoryId());
    }

    @Override
    public List<CopyOfBook> findAllWithBook() throws DaoException {
        return queryOperator.executeQuery(FIND_ALL_WITH_BOOK_QUERY);
    }

    @Override
    public List<CopyOfBook> findByIsbn(String bookIsbn) throws DaoException {
        return queryOperator.executeQuery(FIND_BY_ISBN_QUERY, bookIsbn);
    }

    @Override
    public List<CopyOfBook> findByIsbnAndStatusId(String bookIsbn, int statusId) throws DaoException {
        return queryOperator.executeQuery(FIND_BY_ISBN_AND_STATUS_ID_QUERY, bookIsbn, statusId);
    }
}
