package com.epam.ofeitus.library.dao;

import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.entity.book.Author;
import com.epam.ofeitus.library.entity.book.Book;

import java.util.List;

/**
 * Book dao interface.
 */
public interface BookDao extends AbstractDao<Book> {
    /**
     * Save book to data source.
     *
     * @param entity  book entity
     * @param authors list of authors
     * @return result of data source query
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    int save(Book entity, List<Author> authors) throws DaoException;

    /**
     * Update book.
     *
     * @param entity  book entity
     * @param authors list of authors
     * @return result of data source query
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    int update(Book entity, List<Author> authors) throws DaoException;

    /**
     * Delete book by isbn.
     *
     * @param bookIsbn book isbn
     * @return result of data source query
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    int deleteByIsbn(String bookIsbn) throws DaoException;

    /**
     * Find book by isbn.
     *
     * @param isbn book isbn
     * @return book
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    Book findByIsbn(String isbn) throws DaoException;

    /**
     * Find books matching the search request.
     *
     * @param searchRequest book title or isbn or key word
     * @param categoryId    book category id
     * @param authorId      book author id
     * @param yearFrom      book year from
     * @param yearTo        book year to
     * @param offset        offset
     * @param itemsOnPage   items on page
     * @return books list
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    List<Book> findBySearchRequest(String searchRequest, int categoryId, int authorId, int yearFrom, int yearTo, int offset, int itemsOnPage) throws DaoException;

    /**
     * Count books matching the search request.
     *
     * @param searchRequest book title or isbn or key word
     * @param categoryId    book category id
     * @param authorId      book author id
     * @param yearFrom      book year from
     * @param yearTo        book year to
     * @return books count
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    int countBySearchRequest(String searchRequest, int categoryId, int authorId, int yearFrom, int yearTo) throws DaoException;
}
