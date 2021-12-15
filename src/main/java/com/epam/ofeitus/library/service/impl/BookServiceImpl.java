package com.epam.ofeitus.library.service.impl;

import com.epam.ofeitus.library.dao.AuthorDao;
import com.epam.ofeitus.library.dao.BookCategoryDao;
import com.epam.ofeitus.library.dao.BookDao;
import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.dao.factory.impl.MySqlDaoFactory;
import com.epam.ofeitus.library.entity.book.Author;
import com.epam.ofeitus.library.entity.book.Book;
import com.epam.ofeitus.library.entity.book.constituent.BookCategory;
import com.epam.ofeitus.library.entity.dto.BookDto;
import com.epam.ofeitus.library.service.BookService;
import com.epam.ofeitus.library.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {
    @Override
    public List<BookDto> getAllBooksDto() throws ServiceException {
        BookDao bookDao = MySqlDaoFactory.getInstance().getBookDao();
        AuthorDao authorDao = MySqlDaoFactory.getInstance().getAuthorDao();
        BookCategoryDao bookCategoryDao = MySqlDaoFactory.getInstance().getBookCategoryDao();

        try {
            List<Book> books = bookDao.findAll();
            List<BookDto> booksDto = new ArrayList<>();
            for (Book book : books) {
                List<Author> authors = authorDao.findByBookIsbn(book.getIsbn());
                BookCategory category = bookCategoryDao.findById(book.getCategoryId());
                booksDto.add(new BookDto(
                        book.getIsbn(),
                        book.getTitle(),
                        authors,
                        book.getPublicationYear(),
                        category.getName(),
                        book.getLanguage()));
            }
            return booksDto;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
