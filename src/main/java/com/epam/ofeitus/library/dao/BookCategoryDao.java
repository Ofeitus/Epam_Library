package com.epam.ofeitus.library.dao;

import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.entity.book.constituent.BookCategory;

public interface BookCategoryDao extends AbstractDao<BookCategory> {
    BookCategory findByName(String name) throws DaoException;
}
