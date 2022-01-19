package com.epam.ofeitus.library.dao.impl;

import com.epam.ofeitus.library.constant.Column;
import com.epam.ofeitus.library.constant.Table;
import com.epam.ofeitus.library.dao.CopyOfBookDao;
import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.dao.queryoperator.ParametrizedQuery;
import com.epam.ofeitus.library.dao.rowmapper.RowMapperFactory;
import com.epam.ofeitus.library.entity.book.CopyOfBook;
import com.epam.ofeitus.library.entity.book.constituent.BookCategory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MySqlCopyOfBookDao extends AbstractMySqlDao<CopyOfBook> implements CopyOfBookDao {
    public final static String SAVE_COPY_OF_BOOK_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s, %s, %s) VALUES (0, ?, ?, ?, ?)",
            Table.COPY_OF_BOOK_TABLE,
            Column.COPY_OF_BOOK_INVENTORY_ID,
            Column.COPY_OF_BOOK_RECEIPT_DATE,
            Column.COPY_OF_BOOK_PRICE,
            Column.BOOK_ISBN,
            Column.COPY_OF_BOOK_STATUS_ID);
    public final static String UPDATE_COPY_OF_BOOK_QUERY = String.format(
            "UPDATE %s SET %s=?, %s=?, %s=? WHERE %s=?",
            Table.COPY_OF_BOOK_TABLE,
            Column.COPY_OF_BOOK_RECEIPT_DATE,
            Column.BOOK_ISBN,
            Column.COPY_OF_BOOK_STATUS_ID,
            Column.COPY_OF_BOOK_INVENTORY_ID);
    private static final String FIND_ALL_QUERY = String.format(
            "SELECT * FROM %s WHERE %s!='5' ORDER BY %s LIMIT ?, ?",
            Table.COPY_OF_BOOK_TABLE,
            Column.COPY_OF_BOOK_STATUS_ID,
            Column.COPY_OF_BOOK_INVENTORY_ID);
    private static final String COUNT_ALL_QUERY = String.format(
            "SELECT COUNT(*) FROM %s WHERE %s!='5' AND %s <= ?",
            Table.COPY_OF_BOOK_TABLE,
            Column.COPY_OF_BOOK_STATUS_ID,
            Column.COPY_OF_BOOK_RECEIPT_DATE);
    private static final String FIND_BY_ISBN_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=? AND %s!='5'",
            Table.COPY_OF_BOOK_TABLE,
            Column.BOOK_ISBN,
            Column.COPY_OF_BOOK_STATUS_ID);
    private static final String FIND_BY_ISBN_AND_STATUS_ID_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=? AND %s=?",
            Table.COPY_OF_BOOK_TABLE,
            Column.BOOK_ISBN,
            Column.COPY_OF_BOOK_STATUS_ID);
    private static final String UPDATE_STATUS_BY_ID_QUERY = String.format(
            "UPDATE %s SET %s=? WHERE %s=?",
            Table.COPY_OF_BOOK_TABLE,
            Column.COPY_OF_BOOK_STATUS_ID,
            Column.COPY_OF_BOOK_INVENTORY_ID);
    private static final String COUNT_BY_CATEGORY_QUERY = String.format(
            "SELECT COUNT(*) FROM %s JOIN %s b on %s.%s = b.%s JOIN %s bc on bc.%s = b.%s WHERE %s != '5' AND %s=? AND %s <= ?",
            Table.COPY_OF_BOOK_TABLE,
            Table.BOOK_TABLE,
            Table.COPY_OF_BOOK_TABLE,
            Column.COPY_OF_BOOK_ISBN,
            Column.BOOK_ISBN,
            Table.BOOK_CATEGORY_TABLE,
            Column.CATEGORY_ID,
            Column.CATEGORY_ID,
            Column.COPY_OF_BOOK_STATUS_ID,
            Column.CATEGORY_NAME,
            Column.COPY_OF_BOOK_RECEIPT_DATE);
    private static final String FIND_BY_CATEGORY_QUERY = String.format(
            "SELECT * FROM %s JOIN %s b on %s.%s = b.%s JOIN %s bc on bc.%s = b.%s WHERE %s != '5' AND %s=? AND %s <= ?",
            Table.COPY_OF_BOOK_TABLE,
            Table.BOOK_TABLE,
            Table.COPY_OF_BOOK_TABLE,
            Column.COPY_OF_BOOK_ISBN,
            Column.BOOK_ISBN,
            Table.BOOK_CATEGORY_TABLE,
            Column.CATEGORY_ID,
            Column.CATEGORY_ID,
            Column.COPY_OF_BOOK_STATUS_ID,
            Column.CATEGORY_NAME,
            Column.COPY_OF_BOOK_RECEIPT_DATE);

    public MySqlCopyOfBookDao() {
        super(RowMapperFactory.getCopyOfBookRowMapper(), Table.COPY_OF_BOOK_TABLE, Column.COPY_OF_BOOK_INVENTORY_ID);
    }

    @Override
    public int save(CopyOfBook entity) throws DaoException {
        return queryOperator.executeUpdate(
                SAVE_COPY_OF_BOOK_QUERY,
                entity.getReceiptDate(),
                entity.getBookIsbn(),
                entity.getCopyOfBookStatus().ordinal() + 1);
    }

    @Override
    public int saveAll(List<CopyOfBook> copiesOfBook) throws DaoException {
        List<ParametrizedQuery> parametrizedQueries = new ArrayList<>();
        for (CopyOfBook copyOfBook : copiesOfBook) {
            parametrizedQueries.add(new ParametrizedQuery(
                    SAVE_COPY_OF_BOOK_QUERY,
                    copyOfBook.getReceiptDate(),
                    copyOfBook.getPrice(),
                    copyOfBook.getBookIsbn(),
                    copyOfBook.getCopyOfBookStatus().ordinal() + 1
            ));
        }

        return queryOperator.executeTransaction(parametrizedQueries);
    }

    @Override
    public int update(CopyOfBook entity) throws DaoException {
        return queryOperator.executeUpdate(
                UPDATE_COPY_OF_BOOK_QUERY,
                entity.getReceiptDate(),
                entity.getBookIsbn(),
                entity.getCopyOfBookStatus().ordinal() + 1,
                entity.getInventoryId());
    }

    @Override
    public List<CopyOfBook> findAllExisting(int offset, int itemsOnPage) throws DaoException {
        return queryOperator.executeQuery(FIND_ALL_QUERY, offset, itemsOnPage);
    }

    @Override
    public int countAllExisting(Date date) throws DaoException {
        return queryOperator.executeCountQuery(COUNT_ALL_QUERY, date);
    }

    @Override
    public List<CopyOfBook> findByIsbn(String bookIsbn) throws DaoException {
        return queryOperator.executeQuery(FIND_BY_ISBN_QUERY, bookIsbn);
    }

    @Override
    public List<CopyOfBook> findByIsbnAndStatusId(String bookIsbn, int statusId) throws DaoException {
        return queryOperator.executeQuery(FIND_BY_ISBN_AND_STATUS_ID_QUERY, bookIsbn, statusId);
    }

    @Override
    public List<CopyOfBook> findBySearchRequest(String bookIsbn, int inventoryId, int statusId, int offset, int itemsOnPage) throws DaoException {
        List<Object> parameters = new ArrayList<>();

        String FIND_BY_SEARCH_REQUEST_QUERY = String.format(
                "SELECT * FROM %s ",
                Table.COPY_OF_BOOK_TABLE);

        if (!bookIsbn.equals("")) {
            FIND_BY_SEARCH_REQUEST_QUERY += String.format(
                    "WHERE %s=? ",
                    Column.COPY_OF_BOOK_ISBN);
            parameters.add(bookIsbn);
        } else {
            FIND_BY_SEARCH_REQUEST_QUERY += "WHERE 1=1 ";
        }

        if (inventoryId != 0) {
            FIND_BY_SEARCH_REQUEST_QUERY += String.format(
                    "AND %s=? ",
                    Column.COPY_OF_BOOK_INVENTORY_ID);
            parameters.add(inventoryId);
        }

        if (statusId != 0) {
            if (statusId == 5) {
                FIND_BY_SEARCH_REQUEST_QUERY += String.format(
                        "AND %s=? ",
                        Column.COPY_OF_BOOK_STATUS_ID);
                parameters.add(statusId);
            } else {
                FIND_BY_SEARCH_REQUEST_QUERY += String.format(
                        "AND %s!=? ",
                        Column.COPY_OF_BOOK_STATUS_ID);
                parameters.add(-statusId);
            }
        }

        FIND_BY_SEARCH_REQUEST_QUERY += String.format(
                "ORDER BY %s ",
                Column.COPY_OF_BOOK_INVENTORY_ID
        );

        FIND_BY_SEARCH_REQUEST_QUERY += "LIMIT ?, ?";
        parameters.add(offset);
        parameters.add(itemsOnPage);

        return queryOperator.executeQuery(
                FIND_BY_SEARCH_REQUEST_QUERY,
                parameters.toArray()
        );
    }

    @Override
    public int countBySearchRequest(String bookIsbn, int inventoryId, int statusId) throws DaoException {
        List<Object> parameters = new ArrayList<>();

        String FIND_BY_SEARCH_REQUEST_QUERY = String.format(
                "SELECT COUNT(*) FROM %s ",
                Table.COPY_OF_BOOK_TABLE);

        if (!bookIsbn.equals("")) {
            FIND_BY_SEARCH_REQUEST_QUERY += String.format(
                    "WHERE %s=? ",
                    Column.COPY_OF_BOOK_ISBN);
            parameters.add(bookIsbn);
        } else {
            FIND_BY_SEARCH_REQUEST_QUERY += "WHERE 1=1 ";
        }

        if (inventoryId != 0) {
            FIND_BY_SEARCH_REQUEST_QUERY += String.format(
                    "AND %s=? ",
                    Column.COPY_OF_BOOK_INVENTORY_ID);
            parameters.add(inventoryId);
        }

        if (statusId != 0) {
            if (statusId > 0) {
                FIND_BY_SEARCH_REQUEST_QUERY += String.format(
                        "AND %s=? ",
                        Column.COPY_OF_BOOK_STATUS_ID);
                parameters.add(statusId);
            } else {
                FIND_BY_SEARCH_REQUEST_QUERY += String.format(
                        "AND %s!=? ",
                        Column.COPY_OF_BOOK_STATUS_ID);
                parameters.add(-statusId);
            }
        }

        FIND_BY_SEARCH_REQUEST_QUERY += String.format(
                "ORDER BY %s",
                Column.COPY_OF_BOOK_INVENTORY_ID
        );

        return queryOperator.executeCountQuery(
                FIND_BY_SEARCH_REQUEST_QUERY,
                parameters.toArray()
        );
    }

    @Override
    public int updateStatus(int inventoryId, int statusId) throws DaoException {
        return queryOperator.executeUpdate(UPDATE_STATUS_BY_ID_QUERY, statusId, inventoryId);
    }

    @Override
    public int countByCategory(BookCategory bookCategory, Date date) throws DaoException {
        return queryOperator.executeCountQuery(COUNT_BY_CATEGORY_QUERY, bookCategory.getName(), date);
    }

    @Override
    public List<CopyOfBook> findByCategory(BookCategory bookCategory, Date date) throws DaoException {
        return queryOperator.executeQuery(FIND_BY_CATEGORY_QUERY, bookCategory.getName(), date);
    }
}
