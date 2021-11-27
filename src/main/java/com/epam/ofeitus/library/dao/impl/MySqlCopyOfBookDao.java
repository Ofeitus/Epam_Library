package com.epam.ofeitus.library.dao.impl;

import com.epam.ofeitus.library.dao.CopyOfBookDao;
import com.epam.ofeitus.library.entity.book.Book;
import com.epam.ofeitus.library.entity.book.CopyOfBook;

public class MySqlCopyOfBookDao extends AbstractMySqlDao<CopyOfBook> implements CopyOfBookDao {
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
    public int deleteById(int id) {
        // TODO
        return 0;
    }

    @Override
    public Book getBookByIsbn(String bookIsbn) {
        // TODO
        return null;
    }
}
