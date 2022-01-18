package com.epam.ofeitus.library.dao;

import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.entity.book.CopyOfBook;
import com.epam.ofeitus.library.entity.book.constituent.BookCategory;

import java.util.Date;
import java.util.List;

public interface CopyOfBookDao extends AbstractDao<CopyOfBook> {
    int saveAll(List<CopyOfBook> copiesOfBook) throws DaoException;

    List<CopyOfBook> findAllExisting(int offset, int itemsOnPage) throws DaoException;

    int countAllExisting(Date date) throws DaoException;

    List<CopyOfBook> findByIsbn(String bookIsbn) throws DaoException;

    List<CopyOfBook> findByIsbnAndStatusId(String bookIsbn, int statusId) throws DaoException;

    List<CopyOfBook> findBySearchRequest(String bookIsbn, int inventoryId, int statusId, int offset, int itemsOnPage) throws DaoException;

    int countBySearchRequest(String bookIsbn, int inventoryId, int statusId) throws DaoException;

    int updateStatus(int inventoryId, int statusId) throws DaoException;

    int countByCategory(BookCategory bookCategory, Date date) throws DaoException;
}
