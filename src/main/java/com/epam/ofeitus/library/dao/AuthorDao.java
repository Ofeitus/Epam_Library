package com.epam.ofeitus.library.dao;

import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.entity.book.Author;

import java.util.List;

public interface AuthorDao extends AbstractDao<Author> {
    Author findByName(String name, String surName) throws DaoException;

    List<Author> findByBookIsbn(String isbn) throws DaoException;
}
