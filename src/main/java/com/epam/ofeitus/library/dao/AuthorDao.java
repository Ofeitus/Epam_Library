package com.epam.ofeitus.library.dao;

import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.entity.book.Author;

import java.util.List;

/**
 * Author dao interface.
 */
public interface AuthorDao extends AbstractDao<Author> {
    /**
     * Find by name author.
     *
     * @param name    name
     * @param surName surname
     * @return found author
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    Author findByName(String name, String surName) throws DaoException;

    /**
     * Find by book isbn.
     *
     * @param isbn book isbn
     * @return list of found authors
     * @throws DaoException thrown when dao exception occurs while executing a query
     */
    List<Author> findByBookIsbn(String isbn) throws DaoException;
}
