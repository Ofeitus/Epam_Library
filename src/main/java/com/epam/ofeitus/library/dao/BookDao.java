package com.epam.ofeitus.library.dao;

import com.epam.ofeitus.library.entity.book.Book;

import java.util.List;

public interface BookDao extends AbstractDao<Book> {
    List<Book> getCatalog();

    int getNumberOfCopies(String isbn);

    Book findByIsbn(String isbn);

    List<Book> findByTitle(String title);

    List<Book> findByAuthorId(int authorId);

    List<Book> findByCategoryId(int categoryId);
}
