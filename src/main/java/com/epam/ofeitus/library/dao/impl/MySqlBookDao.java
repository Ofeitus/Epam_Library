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
    public static final String SAVE_BOOK_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?)",
            Table.BOOK_TABLE,
            Column.BOOK_ISBN,
            Column.BOOK_TITLE,
            Column.BOOK_PUBLICATION_YEAR,
            Column.BOOK_CATEGORY_ID,
            Column.BOOK_LANGUAGE,
            Column.BOOK_KEY_WORDS);
    public static final String ADD_AUTHOR_TO_BOOK_QUERY = String.format(
            "INSERT INTO %s (%s, %s) VALUES (?, ?)",
            Table.BOOK_HAS_AUTHOR_TABLE,
            Column.BOOK_ISBN,
            Column.AUTHOR_ID);
    private static final String DELETE_AUTHORS_TO_BOOK_QUERY = String.format(
            "DELETE FROM %s WHERE %s=?",
            Table.BOOK_HAS_AUTHOR_TABLE,
            Column.BOOK_ISBN);
    public static final String UPDATE_BOOK_QUERY = String.format(
            "UPDATE %s SET %s=?, %s=?, %s=?, %s=?, %s=? WHERE %s=?",
            Table.BOOK_TABLE,
            Column.BOOK_TITLE,
            Column.BOOK_PUBLICATION_YEAR,
            Column.BOOK_CATEGORY_ID,
            Column.BOOK_LANGUAGE,
            Column.BOOK_KEY_WORDS,
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
        return queryOperator.executeUpdate(
                SAVE_BOOK_QUERY,
                entity.getIsbn(),
                entity.getTitle(),
                entity.getPublicationYear(),
                entity.getCategoryId(),
                entity.getLanguage(),
                entity.getKeyWords()
        );
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
                entity.getLanguage(),
                entity.getKeyWords()
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
                entity.getKeyWords(),
                entity.getIsbn()
        );
    }

    @Override
    public int update(Book entity, List<Author> authors) throws DaoException {
        List<ParametrizedQuery> parametrizedQueries = new ArrayList<>();
        parametrizedQueries.add(new ParametrizedQuery(
                UPDATE_BOOK_QUERY,
                entity.getTitle(),
                entity.getPublicationYear(),
                entity.getCategoryId(),
                entity.getLanguage(),
                entity.getKeyWords(),
                entity.getIsbn()
        ));
        parametrizedQueries.add(new ParametrizedQuery(
                DELETE_AUTHORS_TO_BOOK_QUERY,
                entity.getIsbn()
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
    public int deleteByIsbn(String bookIsbn) throws DaoException {
        List<ParametrizedQuery> parametrizedQueries = new ArrayList<>();
        parametrizedQueries.add(new ParametrizedQuery(
                DELETE_AUTHORS_TO_BOOK_QUERY,
                bookIsbn
        ));
        parametrizedQueries.add(new ParametrizedQuery(
                DELETE_BY_ID_QUERY,
                bookIsbn
        ));
        return queryOperator.executeTransaction(parametrizedQueries);
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
    public List<Book> findBySearchRequest(String searchRequest, int categoryId, int authorId, int yearFrom, int yearTo, int offset, int itemsOnPage) throws DaoException {
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
                    Column.BOOK_HAS_AUTHOR_BOOK_ISBN,
                    Column.AUTHOR_ID);
            parameters.add(authorId);
        } else {
            FIND_BY_SEARCH_REQUEST_QUERY += "WHERE 1=1 ";
        }

        if (!searchRequest.equals("")) {
            FIND_BY_SEARCH_REQUEST_QUERY += String.format(
                    "AND (%s.%s=? OR %s LIKE ? OR %s LIKE ?) ",
                    Table.BOOK_TABLE,
                    Column.BOOK_ISBN,
                    Column.BOOK_TITLE,
                    Column.BOOK_KEY_WORDS);
            parameters.add(searchRequest);
            parameters.add("%" + searchRequest + "%");
            parameters.add("%" + searchRequest + "%");
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

        FIND_BY_SEARCH_REQUEST_QUERY += "LIMIT ?, ?";
        parameters.add(offset);
        parameters.add(itemsOnPage);

        return queryOperator.executeQuery(
                FIND_BY_SEARCH_REQUEST_QUERY,
                parameters.toArray()
        );
    }

    @Override
    public int countBySearchRequest(String searchRequest, int categoryId, int authorId, int yearFrom, int yearTo) throws DaoException {
        List<Object> parameters = new ArrayList<>();

        String FIND_BY_SEARCH_REQUEST_QUERY = String.format(
                "SELECT COUNT(*) FROM %s ",
                Table.BOOK_TABLE);

        if (authorId != 0) {
            FIND_BY_SEARCH_REQUEST_QUERY += String.format(
                    "JOIN %s BhA ON %s.%s = BhA.%s WHERE %s=? ",
                    Table.BOOK_HAS_AUTHOR_TABLE,
                    Table.BOOK_TABLE,
                    Column.BOOK_ISBN,
                    Column.BOOK_HAS_AUTHOR_BOOK_ISBN,
                    Column.AUTHOR_ID);
            parameters.add(authorId);
        } else {
            FIND_BY_SEARCH_REQUEST_QUERY += "WHERE 1=1 ";
        }

        if (!searchRequest.equals("")) {
            FIND_BY_SEARCH_REQUEST_QUERY += String.format(
                    "AND (%s.%s=? OR %s LIKE ? OR %s LIKE ?) ",
                    Table.BOOK_TABLE,
                    Column.BOOK_ISBN,
                    Column.BOOK_TITLE,
                    Column.BOOK_KEY_WORDS);
            parameters.add(searchRequest);
            parameters.add("%" + searchRequest + "%");
            parameters.add("%" + searchRequest + "%");
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

        return queryOperator.executeCountQuery(
                FIND_BY_SEARCH_REQUEST_QUERY,
                parameters.toArray()
        );
    }
}
