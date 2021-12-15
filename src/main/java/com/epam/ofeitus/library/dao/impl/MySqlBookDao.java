package com.epam.ofeitus.library.dao.impl;

import com.epam.ofeitus.library.constant.Column;
import com.epam.ofeitus.library.constant.Table;
import com.epam.ofeitus.library.dao.BookDao;
import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.dao.queryoperator.ParametrizedQuery;
import com.epam.ofeitus.library.dao.rowmapper.RowMapperFactory;
import com.epam.ofeitus.library.entity.book.Author;
import com.epam.ofeitus.library.entity.book.Book;

import java.util.ArrayList;
import java.util.List;

public class MySqlBookDao extends AbstractMySqlDao<Book> implements BookDao {
    public final static String SAVE_BOOK_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?)",
            Table.BOOK_TABLE,
            Column.BOOK_ISBN,
            Column.BOOK_TITLE,
            Column.BOOK_PUBLICATION_YEAR,
            Column.BOOK_CATEGORY_ID,
            Column.BOOK_LANGUAGE);
    public final static String ADD_AUTHOR_TO_BOOK_QUERY = String.format(
            "INSERT INTO %s (%s, %s) VALUES (?, ?)",
            Table.BOOK_HAS_AUTHOR_TABLE,
            Column.BOOK_ISBN,
            Column.AUTHOR_ID);
    public final static String UPDATE_BOOK_QUERY = String.format(
            "UPDATE %s SET %s=?, %s=?, %s=?, %s=? WHERE %s=?",
            Table.BOOK_TABLE,
            Column.BOOK_TITLE,
            Column.BOOK_PUBLICATION_YEAR,
            Column.BOOK_CATEGORY_ID,
            Column.BOOK_LANGUAGE,
            Column.BOOK_ISBN);
    private static final String FIND_BY_ISBN_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=?",
            Table.BOOK_TABLE,
            Column.BOOK_ISBN);
    private static final String FIND_BY_TITLE_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=?",
            Table.BOOK_TABLE,
            Column.BOOK_TITLE);
    private static final String FIND_BY_AUTHOR_ID_QUERY = String.format(
            "SELECT * FROM %s JOIN %s BhA ON %s.%s = BhA.%s WHERE %s=?",
            Table.BOOK_TABLE,
            Table.BOOK_HAS_AUTHOR_TABLE,
            Table.BOOK_TABLE,
            Column.BOOK_ISBN,
            Column.BOOK_ISBN,
            Column.AUTHOR_ID);
    private static final String FIND_BY_CATEGORY_ID_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=?",
            Table.BOOK_TABLE,
            Column.BOOK_CATEGORY_ID);

    public MySqlBookDao() {
        super(RowMapperFactory.getBookRowMapper(), Table.BOOK_TABLE, Column.BOOK_ISBN);
    }

    @Override
    public int save(Book entity) throws DaoException {
        throw new DaoException("Unsupported operation for Books table.");
    }

    @Override
    public int save(Book entity, List<Author> authors) throws DaoException {
        List<ParametrizedQuery> parametrizedQueries = new ArrayList<>();
        parametrizedQueries.add(new ParametrizedQuery(
                SAVE_BOOK_QUERY,
                entity.getIsbn(),
                entity.getTitle(),
                entity.getPublicationYear(),
                entity.getCategoryId(),
                entity.getLanguage()
        ));
        for (Author author : authors) {
            parametrizedQueries.add(new ParametrizedQuery(
                    ADD_AUTHOR_TO_BOOK_QUERY,
                    entity.getIsbn(),
                    author.getAuthorId()
            ));
        }
        return queryOperator.executeTransaction(parametrizedQueries);
    }

    @Override
    public int update(Book entity) throws DaoException {
        return queryOperator.executeUpdate(
                UPDATE_BOOK_QUERY,
                entity.getTitle(),
                entity.getPublicationYear(),
                entity.getCategoryId(),
                entity.getLanguage(),
                entity.getIsbn()
        );
    }

    @Override
    public Book findByIsbn(String isbn) throws DaoException {
        return queryOperator.executeSingleEntityQuery(FIND_BY_ISBN_QUERY, isbn);
    }

    @Override
    public List<Book> findByTitle(String title) throws DaoException {
        return queryOperator.executeQuery(FIND_BY_TITLE_QUERY, title);
    }

    @Override
    public List<Book> findByAuthorId(int authorId) throws DaoException {
        return queryOperator.executeQuery(FIND_BY_AUTHOR_ID_QUERY, authorId);
    }

    @Override
    public List<Book> findByCategoryId(int categoryId) throws DaoException {
        return queryOperator.executeQuery(FIND_BY_CATEGORY_ID_QUERY, categoryId);
    }

    @Override
    public List<Book> findBySearchRequest(String isbnOrTitle, int categoryId, int authorId, int yearFrom, int yearTo) throws DaoException {
        List<Object> parameters = new ArrayList<>();

        String FIND_BY_SEARCH_REQUEST_QUERY = String.format(
                "SELECT * FROM %s ",
                Table.BOOK_TABLE);

        if (authorId != 0) {
            FIND_BY_SEARCH_REQUEST_QUERY += String.format(
                    "JOIN %s BhA ON %s.%s = BhA.%s WHERE %s=? ",
                    Table.BOOK_HAS_AUTHOR_TABLE,
                    Table.BOOK_TABLE,
                    Column.BOOK_ISBN,
                    Column.BOOK_ISBN,
                    Column.AUTHOR_ID);
            parameters.add(authorId);
        } else {
            FIND_BY_SEARCH_REQUEST_QUERY += "WHERE 1=1 ";
        }

        if (!isbnOrTitle.equals("")) {
            FIND_BY_SEARCH_REQUEST_QUERY += String.format(
                    "AND (%s.%s=? OR %s=?) ",
                    Table.BOOK_TABLE,
                    Column.BOOK_ISBN,
                    Column.BOOK_TITLE);
            parameters.add(isbnOrTitle);
            parameters.add(isbnOrTitle);
        }

        if (yearFrom != 0 || yearTo != 0) {
            FIND_BY_SEARCH_REQUEST_QUERY += String.format(
                    "AND (%s BETWEEN ? AND ?) ",
                    Column.BOOK_PUBLICATION_YEAR);
            parameters.add(yearFrom);
            parameters.add(yearTo);
        }

        if (categoryId != 0) {
            FIND_BY_SEARCH_REQUEST_QUERY += String.format(
                    "AND %s=? ",
                    Column.CATEGORY_ID);
            parameters.add(categoryId);
        }

        if (parameters.size() == 0) {
            return new ArrayList<>();
        }

        return queryOperator.executeQuery(
                FIND_BY_SEARCH_REQUEST_QUERY,
                parameters.toArray()
        );
    }
}
