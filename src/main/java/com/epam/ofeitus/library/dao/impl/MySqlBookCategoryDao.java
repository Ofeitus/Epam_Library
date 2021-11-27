package com.epam.ofeitus.library.dao.impl;

import com.epam.ofeitus.library.dao.BookCategoryDao;
import com.epam.ofeitus.library.entity.book.constituents.BookCategory;

public class MySqlBookCategoryDao extends AbstractMySqlDao<BookCategory> implements BookCategoryDao {
    @Override
    public int save(BookCategory entity) {
        // TODO
        return 0;
    }

    @Override
    public int update(BookCategory entity) {
        // TODO
        return 0;
    }

    @Override
    public int deleteById(int id) {
        // TODO
        return 0;
    }

    @Override
    public BookCategory findByName(String name) {
        // TODO
        return null;
    }
}
