package com.epam.ofeitus.library.dao.impl;

import com.epam.ofeitus.library.constant.Column;
import com.epam.ofeitus.library.constant.Table;
import com.epam.ofeitus.library.dao.BookCategoryDao;
import com.epam.ofeitus.library.dao.rowmapper.RowMapperFactory;
import com.epam.ofeitus.library.entity.book.constituent.BookCategory;

public class MySqlBookCategoryDao extends AbstractMySqlDao<BookCategory> implements BookCategoryDao {
    public MySqlBookCategoryDao() {
        super(RowMapperFactory.getBookCategoryRowMapper(), Table.BOOK_CATEGORY_TABLE, Column.BOOK_CATEGORY_ID);
    }

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
    public BookCategory findByName(String name) {
        // TODO
        return null;
    }
}
