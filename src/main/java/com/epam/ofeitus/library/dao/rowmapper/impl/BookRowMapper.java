package com.epam.ofeitus.library.dao.rowmapper.impl;

import com.epam.ofeitus.library.dao.rowmapper.Column;
import com.epam.ofeitus.library.dao.rowmapper.RowMapper;
import com.epam.ofeitus.library.entity.book.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {
    @Override
    public Book map(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setIsbn(resultSet.getString(Column.BOOK_ISBN));
        book.setTitle(resultSet.getString(Column.BOOK_TITLE));
        book.setPublicationYear(resultSet.getInt(Column.BOOK_PUBLICATION_YEAR));
        book.setCategoryId(resultSet.getInt(Column.BOOK_CATEGORY_ID));
        book.setLanguage(resultSet.getString(Column.BOOK_LANGUAGE));
        return book;
    }
}
