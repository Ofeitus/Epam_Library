package com.epam.ofeitus.library.dao;

import com.epam.ofeitus.library.entity.book.Book;
import com.epam.ofeitus.library.entity.book.CopyOfBook;

public interface CopyOfBookDao extends AbstractDao<CopyOfBook> {
    Book getBookByIsbn(String bookIsbn);
}
