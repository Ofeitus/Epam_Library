package com.epam.ofeitus.library.dao.rowmapper.impl;

import com.epam.ofeitus.library.dao.rowmapper.Column;
import com.epam.ofeitus.library.dao.rowmapper.RowMapper;
import com.epam.ofeitus.library.entity.book.CopyOfBook;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CopyOfBookRowMapper implements RowMapper<CopyOfBook> {
    @Override
    public CopyOfBook map(ResultSet resultSet) throws SQLException {
        CopyOfBook copyOfBook = new CopyOfBook();
        copyOfBook.setInventoryId(resultSet.getInt(Column.BOOK_INVENTORY_ID));
        copyOfBook.setBookIsbn(resultSet.getString(Column.BOOK_ISBN));
        return copyOfBook;
    }
}
