package com.epam.ofeitus.library.dao;

import com.epam.ofeitus.library.entity.book.Author;

public interface AuthorDao extends AbstractDao<Author> {
    Author findByName(String name, String surName);
}
