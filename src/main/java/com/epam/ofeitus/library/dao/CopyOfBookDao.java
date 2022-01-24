package com.epam.ofeitus.library.dao;

import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.entity.book.CopyOfBook;
import com.epam.ofeitus.library.entity.book.constituent.BookCategory;

import java.util.Date;
import java.util.List;

/**
 * Copy of book dao interface.
 */
public interface CopyOfBookDao extends AbstractDao<CopyOfBook> {
    /**
     * Save all copies of book to the data source.
     *
     * @param copiesOfBook list of copies of book
     * @return result of data source query
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    int saveAll(List<CopyOfBook> copiesOfBook) throws DaoException;

    /**
     * Set copy of book status.
     *
     * @param inventoryId inventory id
     * @param statusId    status id
     * @return result of data source query
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    int updateStatus(int inventoryId, int statusId) throws DaoException;

    /**
     * Find by isbn.
     *
     * @param bookIsbn book isbn
     * @return copies of books list
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    List<CopyOfBook> findByIsbn(String bookIsbn) throws DaoException;

    /**
     * Find by isbn and status id.
     *
     * @param bookIsbn book isbn
     * @param statusId status id
     * @return copies of books list
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    List<CopyOfBook> findByIsbnAndStatusId(String bookIsbn, int statusId) throws DaoException;

    /**
     * Find by category and written in before given date.
     *
     * @param bookCategory book category
     * @param date         date
     * @return copies of books list
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    List<CopyOfBook> findByCategory(BookCategory bookCategory, Date date) throws DaoException;

    /**
     * Count by category and written in before given date.
     *
     * @param bookCategory book category
     * @param date         date
     * @return copies of books count
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    int countByCategory(BookCategory bookCategory, Date date) throws DaoException;

    /**
     * Find all existing.
     *
     * @param offset      offset
     * @param itemsOnPage items on page
     * @return copies of books list
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    List<CopyOfBook> findAllExisting(int offset, int itemsOnPage) throws DaoException;

    /**
     * Count all existing and written in before given date.
     *
     * @param date date
     * @return copies of books count
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    int countAllExisting(Date date) throws DaoException;

    /**
     * Find copies of books matching the search request.
     *
     * @param bookIsbn    book isbn
     * @param inventoryId inventory id
     * @param statusId    status id
     * @param offset      offset
     * @param itemsOnPage items on page
     * @return copies of books list
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    List<CopyOfBook> findBySearchRequest(String bookIsbn, int inventoryId, int statusId, int offset, int itemsOnPage) throws DaoException;

    /**
     * Count copies of books matching the search request.
     *
     * @param bookIsbn    book isbn
     * @param inventoryId inventory id
     * @param statusId    status id
     * @return copies of books count
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    int countBySearchRequest(String bookIsbn, int inventoryId, int statusId) throws DaoException;
}
