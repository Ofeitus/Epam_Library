package com.epam.ofeitus.library.service.impl;

import com.epam.ofeitus.library.dao.*;
import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.dao.factory.DaoFactory;
import com.epam.ofeitus.library.dao.factory.impl.MySqlDaoFactory;
import com.epam.ofeitus.library.entity.book.Author;
import com.epam.ofeitus.library.entity.book.Book;
import com.epam.ofeitus.library.entity.book.CopyOfBook;
import com.epam.ofeitus.library.entity.book.constituent.BookCategory;
import com.epam.ofeitus.library.entity.book.constituent.CopyOfBookStatus;
import com.epam.ofeitus.library.entity.dto.BookDto;
import com.epam.ofeitus.library.entity.order.Reservation;
import com.epam.ofeitus.library.entity.order.constiuent.ReservationStatus;
import com.epam.ofeitus.library.service.BookService;
import com.epam.ofeitus.library.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
                        book.getLanguage(),
                        book.getKeyWords()));
            }
            return booksDto;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<BookDto> getBooksDtoBySearchRequest(String searchRequest, String category, String authorName, String authorSurname, int yearFrom, int yearTo) throws ServiceException {
        DaoFactory daoFactory = MySqlDaoFactory.getInstance();
        BookDao bookDao = daoFactory.getBookDao();
        AuthorDao authorDao = daoFactory.getAuthorDao();
        BookCategoryDao bookCategoryDao = daoFactory.getBookCategoryDao();

        try {
            Author author = authorDao.findByName(authorName, authorSurname);
            int authorId = author != null ? author.getAuthorId() : 0;

            BookCategory bookCategory = bookCategoryDao.findByName(category);
            int categoryId = bookCategory != null ? bookCategory.getCategoryId() : 0;

            List<Book> books = bookDao.findBySearchRequest(searchRequest, categoryId, authorId, yearFrom, yearTo);
            List<BookDto> booksDto = new ArrayList<>();
            for (Book book : books) {
                List<Author> authors = authorDao.findByBookIsbn(book.getIsbn());
                booksDto.add(new BookDto(
                        book.getIsbn(),
                        book.getTitle(),
                        authors,
                        book.getPublicationYear(),
                        category,
                        book.getLanguage(),
                        book.getKeyWords()
                        )
                );
            }
            return booksDto;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public BookDto getBookDtoByIsbn(String bookIsbn) throws ServiceException {
        DaoFactory daoFactory = MySqlDaoFactory.getInstance();
        BookDao bookDao = daoFactory.getBookDao();
        AuthorDao authorDao = daoFactory.getAuthorDao();
        BookCategoryDao bookCategoryDao = daoFactory.getBookCategoryDao();

        try {
            Book book = bookDao.findByIsbn(bookIsbn);
            List<Author> authors = authorDao.findByBookIsbn(book.getIsbn());
            BookCategory category = bookCategoryDao.findById(book.getCategoryId());
            return new BookDto(
                    book.getIsbn(),
                    book.getTitle(),
                    authors,
                    book.getPublicationYear(),
                    category.getName(),
                    book.getLanguage(),
                    book.getKeyWords()
            );
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getAvailableCopiesCount(String bookIsbn) throws ServiceException {
        CopyOfBookDao copyOfBookDao = MySqlDaoFactory.getInstance().getCopyOfBookDao();

        try {
            return copyOfBookDao.findByIsbnAndStatusId(bookIsbn, CopyOfBookStatus.AVAILABLE.ordinal() + 1).size();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
