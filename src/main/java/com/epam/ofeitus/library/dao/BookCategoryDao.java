package com.epam.ofeitus.library.dao;

import com.epam.ofeitus.library.entity.book.constituents.BookCategory;

public interface BookCategoryDao extends AbstractDao<BookCategory> {
    BookCategory findByName(String name);
}
