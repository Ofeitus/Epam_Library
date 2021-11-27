package com.epam.ofeitus.library.dao.impl;

import com.epam.ofeitus.library.dao.AuthorDao;
import com.epam.ofeitus.library.entity.book.Author;

public class MySqlAuthorDao extends AbstractMySqlDao<Author> implements AuthorDao {
    @Override
    public int save(Author entity) {
        // TODO
        return 0;
    }

    @Override
    public int update(Author entity) {
        // TODO
        return 0;
    }

    @Override
    public int deleteById(int id) {
        // TODO
        return 0;
    }

    @Override
    public Author findByName(String firstName, String secondName) {
        // TODO
        return null;
    }
}
