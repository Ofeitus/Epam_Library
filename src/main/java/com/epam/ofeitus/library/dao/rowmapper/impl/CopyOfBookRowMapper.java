package com.epam.ofeitus.library.dao.rowmapper.impl;

import com.epam.ofeitus.library.constant.Column;
import com.epam.ofeitus.library.dao.rowmapper.RowMapper;
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
        copyOfBook.setPrice(resultSet.getBigDecimal(Column.COPY_OF_BOOK_PRICE));
        copyOfBook.setBookIsbn(resultSet.getString(Column.COPY_OF_BOOK_ISBN));
        copyOfBook.setCopyOfBookStatus(CopyOfBookStatus.values()[resultSet.getInt(Column.COPY_OF_BOOK_STATUS_ID) - 1]);
        return copyOfBook;
    }
}
