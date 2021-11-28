package com.epam.ofeitus.library.dao.impl;

import com.epam.ofeitus.library.constant.Column;
import com.epam.ofeitus.library.constant.Table;
import com.epam.ofeitus.library.dao.CopyOfBookDao;
import com.epam.ofeitus.library.dao.rowmapper.RowMapperFactory;
import com.epam.ofeitus.library.entity.book.Book;
import com.epam.ofeitus.library.entity.book.CopyOfBook;

public class MySqlCopyOfBookDao extends AbstractMySqlDao<CopyOfBook> implements CopyOfBookDao {
    public MySqlCopyOfBookDao() {
        super(RowMapperFactory.getCopyOfBookRowMapper(), Table.COPY_OF_BOOK_TABLE, Column.BOOK_INVENTORY_ID);
    }

    @Override
    public int save(CopyOfBook entity) {
        // TODO
        return 0;
    }

    @Override
    public int update(CopyOfBook entity) {
        // TODO
        return 0;
    }

    @Override
    public Book getBookByIsbn(String bookIsbn) {
        // TODO
        return null;
    }
}
