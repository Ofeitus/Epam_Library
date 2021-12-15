package com.epam.ofeitus.library.dao;

import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.entity.book.Author;
import com.epam.ofeitus.library.entity.book.Book;

import java.util.List;

public interface BookDao extends AbstractDao<Book> {
    int save(Book entity, List<Author> authors) throws DaoException;

    Book findByIsbn(String isbn) throws DaoException;

    List<Book> findByTitle(String title) throws DaoException;

    List<Book> findByAuthorId(int authorId) throws DaoException;

    List<Book> findByCategoryId(int categoryId) throws DaoException;

    List<Book> findBySearchRequest(String isbnOrTitle, int categoryId, int authorId, int yearFrom, int yearTo) throws DaoException;
}
