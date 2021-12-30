package com.epam.ofeitus.library.dao;

import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.entity.book.CopyOfBook;

import java.util.List;

public interface CopyOfBookDao extends AbstractDao<CopyOfBook> {
    List<CopyOfBook> findAllWithBook() throws DaoException;

    List<CopyOfBook> findByIsbn(String bookIsbn) throws DaoException;

    List<CopyOfBook> findByIsbnAndStatusId(String bookIsbn, int statusId) throws DaoException;

    List<CopyOfBook> findBySearchRequestWithBook(String bookIsbn, int inventoryId, int statusId) throws DaoException;

    int updateStatus(int inventoryId, int statusId) throws DaoException;
}
