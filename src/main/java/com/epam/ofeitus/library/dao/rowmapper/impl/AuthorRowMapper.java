package com.epam.ofeitus.library.dao.rowmapper.impl;

import com.epam.ofeitus.library.dao.rowmapper.Column;
import com.epam.ofeitus.library.dao.rowmapper.RowMapper;
import com.epam.ofeitus.library.entity.book.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorRowMapper implements RowMapper<Author> {
    @Override
    public Author map(ResultSet resultSet) throws SQLException {
        Author author = new Author();
        author.setAuthorId(resultSet.getInt(Column.AUTHOR_ID));
        author.setName(resultSet.getString(Column.AUTHOR_NAME));
        author.setSurName(resultSet.getString(Column.AUTHOR_SURNAME));
        return author;
    }
}
