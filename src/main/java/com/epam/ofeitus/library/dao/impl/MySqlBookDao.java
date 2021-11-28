package com.epam.ofeitus.library.dao.impl;

import com.epam.ofeitus.library.constant.Column;
import com.epam.ofeitus.library.constant.Table;
import com.epam.ofeitus.library.dao.BookDao;
import com.epam.ofeitus.library.dao.rowmapper.RowMapperFactory;
import com.epam.ofeitus.library.entity.book.Book;

import java.util.List;

public class MySqlBookDao extends AbstractMySqlDao<Book> implements BookDao {
    public MySqlBookDao() {
        super(RowMapperFactory.getBookRowMapper(), Table.BOOK_TABLE, Column.BOOK_ISBN);
    }

    @Override
    public int save(Book entity) {
        // TODO
        return 0;
    }

    @Override
    public int update(Book entity) {
        // TODO
        return 0;
    }

    @Override
    public List<Book> getCatalog() {
        // TODO
        return null;
    }

    @Override
    public int getNumberOfCopies(String isbn) {
        // TODO
        return 0;
    }

    @Override
    public Book findByIsbn(String isbn) {
        // TODO
        return null;
    }

    @Override
    public List<Book> findByTitle(String title) {
        // TODO
        return null;
    }

    @Override
    public List<Book> findByAuthorId(int authorId) {
        // TODO
        return null;
    }

    @Override
    public List<Book> findByCategoryId(int categoryId) {
        // TODO
        return null;
    }
}
