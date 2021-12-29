package com.epam.ofeitus.library.dao.rowmapper.impl;

import com.epam.ofeitus.library.constant.Column;
import com.epam.ofeitus.library.dao.rowmapper.RowMapper;
import com.epam.ofeitus.library.entity.book.Book;
import com.epam.ofeitus.library.entity.book.CopyOfBook;
import com.epam.ofeitus.library.entity.book.constituent.CopyOfBookStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CopyOfBookRowMapper implements RowMapper<CopyOfBook> {
    @Override
    public CopyOfBook map(ResultSet resultSet) throws SQLException {
        CopyOfBook copyOfBook = new CopyOfBook();
        copyOfBook.setInventoryId(resultSet.getInt(Column.COPY_OF_BOOK_INVENTORY_ID));
        copyOfBook.setReceiptDate(resultSet.getDate(Column.COPY_OF_BOOK_RECEIPT_DATE));
        copyOfBook.setBookIsbn(resultSet.getString(Column.COPY_OF_BOOK_ISBN));
        copyOfBook.setCopyOfBookStatus(CopyOfBookStatus.values()[resultSet.getInt(Column.COPY_OF_BOOK_STATUS_ID) - 1]);
        for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
            if (resultSet.getMetaData().getColumnName(i).equals(Column.BOOK_TITLE)) {
                copyOfBook.setBook(new Book(
                        resultSet.getString(Column.BOOK_ISBN),
                        resultSet.getString(Column.BOOK_TITLE),
                        resultSet.getInt(Column.BOOK_PUBLICATION_YEAR),
                        resultSet.getInt(Column.BOOK_CATEGORY_ID),
                        resultSet.getString(Column.BOOK_LANGUAGE),
                        resultSet.getString(Column.BOOK_KEY_WORDS)
                ));
            }
        }
        return copyOfBook;
    }
}
