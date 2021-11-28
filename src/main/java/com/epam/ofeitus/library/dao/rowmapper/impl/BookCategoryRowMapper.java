package com.epam.ofeitus.library.dao.rowmapper.impl;

import com.epam.ofeitus.library.dao.rowmapper.Column;
import com.epam.ofeitus.library.dao.rowmapper.RowMapper;
import com.epam.ofeitus.library.entity.book.constituents.BookCategory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookCategoryRowMapper implements RowMapper<BookCategory> {
    @Override
    public BookCategory map(ResultSet resultSet) throws SQLException {
        BookCategory bookCategory = new BookCategory();
        bookCategory.setCategoryId(resultSet.getInt(Column.CATEGORY_ID));
        bookCategory.setName(resultSet.getString(Column.CATEGORY_NAME));
        return bookCategory;
    }
}
