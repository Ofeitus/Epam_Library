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
import com.epam.ofeitus.library.entity.dto.CopyOfBookDto;
import com.epam.ofeitus.library.service.BookService;
import com.epam.ofeitus.library.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookServiceImpl implements BookService {

    @Override
    public List<BookDto> getAllBooksDto() throws ServiceException {
        BookDao bookDao = MySqlDaoFactory.getInstance().getBookDao();
        AuthorDao authorDao = MySqlDaoFactory.getInstance().getAuthorDao();
        BookCategoryDao bookCategoryDao = MySqlDaoFactory.getInstance().getBookCategoryDao();

        try {
            List<Book> books = bookDao.findAllExisting();
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
    public int getCopiesCount(String bookIsbn) throws ServiceException {
        CopyOfBookDao copyOfBookDao = MySqlDaoFactory.getInstance().getCopyOfBookDao();

        try {
            return copyOfBookDao.findByIsbn(bookIsbn).size();
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

    @Override
    public List<BookCategory> getBookCategories() throws ServiceException {
        BookCategoryDao bookCategoryDao = MySqlDaoFactory.getInstance().getBookCategoryDao();

        try {
            return bookCategoryDao.findAllExisting();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int updateBook(String bookIsbn, String title, String category, int publicationYear, String language, String keyWords, List<Author> authors) throws ServiceException {
        DaoFactory daoFactory = MySqlDaoFactory.getInstance();
        BookDao bookDao = daoFactory.getBookDao();
        BookCategoryDao bookCategoryDao = daoFactory.getBookCategoryDao();
        AuthorDao authorDao = daoFactory.getAuthorDao();

        try {
            int categoryId = bookCategoryDao.findByName(category).getCategoryId();
            for (Author author : authors) {
                Author authorInDb = authorDao.findByName(author.getName(), author.getSurname());
                if (authorInDb == null) {
                    authorDao.save(author);
                    author.setAuthorId(authorDao.findByName(author.getName(), author.getSurname()).getAuthorId());
                } else {
                    author.setAuthorId(authorInDb.getAuthorId());
                }
            }
            return bookDao.update(new Book(bookIsbn, title, publicationYear, categoryId, language, keyWords), authors);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int saveBook(String bookIsbn, String title, String category, int publicationYear, String language, String keyWords, List<Author> authors) throws ServiceException {
        DaoFactory daoFactory = MySqlDaoFactory.getInstance();
        BookDao bookDao = daoFactory.getBookDao();
        BookCategoryDao bookCategoryDao = daoFactory.getBookCategoryDao();
        AuthorDao authorDao = daoFactory.getAuthorDao();

        try {
            int categoryId = bookCategoryDao.findByName(category).getCategoryId();
            for (Author author : authors) {
                Author authorInDb = authorDao.findByName(author.getName(), author.getSurname());
                if (authorInDb == null) {
                    authorDao.save(author);
                    author.setAuthorId(authorDao.findByName(author.getName(), author.getSurname()).getAuthorId());
                } else {
                    author.setAuthorId(authorInDb.getAuthorId());
                }
            }
            return bookDao.save(new Book(bookIsbn, title, publicationYear, categoryId, language, keyWords), authors);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteBook(String bookIsbn) throws ServiceException {
        BookDao bookDao = MySqlDaoFactory.getInstance().getBookDao();
        try {
            bookDao.deleteByIsbn(bookIsbn);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<CopyOfBookDto> getAllCopiesOfBooks() throws ServiceException {
        DaoFactory daoFactory = MySqlDaoFactory.getInstance();
        CopyOfBookDao copyOfBookDao = daoFactory.getCopyOfBookDao();
        ReservationDao reservationDao = daoFactory.getReservationDao();
        LoanDao loanDao = daoFactory.getLoanDao();

        try {
            List<CopyOfBook> copiesOfBooks = copyOfBookDao.findAllExisting();
            List<CopyOfBookDto> copiesOfBooksDto = new ArrayList<>();
            for (CopyOfBook copyOfBook : copiesOfBooks) {
                BookDto bookDto = getBookDtoByIsbn(copyOfBook.getBookIsbn());
                copiesOfBooksDto.add(new CopyOfBookDto(
                                copyOfBook.getInventoryId(),
                                copyOfBook.getReceiptDate(),
                                copyOfBook.getBookIsbn(),
                                copyOfBook.getCopyOfBookStatus(),
                                bookDto,
                                reservationDao.findByInventoryId(copyOfBook.getInventoryId()).size() == 0 &&
                                        loanDao.findByInventoryId(copyOfBook.getInventoryId()).size() == 0
                        )
                );
            }
            return copiesOfBooksDto;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<CopyOfBookDto> getCopiesOfBooksBySearchRequest(String bookIsbn, int inventoryId, int statusId) throws ServiceException {
        DaoFactory daoFactory = MySqlDaoFactory.getInstance();
        CopyOfBookDao copyOfBookDao = daoFactory.getCopyOfBookDao();
        ReservationDao reservationDao = daoFactory.getReservationDao();
        LoanDao loanDao = daoFactory.getLoanDao();

        try {
            List<CopyOfBook> copiesOfBooks = copyOfBookDao.findBySearchRequest(bookIsbn, inventoryId, statusId);
            List<CopyOfBookDto> copiesOfBooksDto = new ArrayList<>();
            for (CopyOfBook copyOfBook : copiesOfBooks) {
                BookDto bookDto = getBookDtoByIsbn(copyOfBook.getBookIsbn());
                copiesOfBooksDto.add(new CopyOfBookDto(
                        copyOfBook.getInventoryId(),
                        copyOfBook.getReceiptDate(),
                        copyOfBook.getBookIsbn(),
                        copyOfBook.getCopyOfBookStatus(),
                        bookDto,
                        reservationDao.findByInventoryId(copyOfBook.getInventoryId()).size() == 0 &&
                                loanDao.findByInventoryId(copyOfBook.getInventoryId()).size() == 0)
                );
            }
            return copiesOfBooksDto;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void writeOffCopyOfBook(int inventoryId) throws ServiceException {
        CopyOfBookDao copyOfBookDao = MySqlDaoFactory.getInstance().getCopyOfBookDao();

        try {
            copyOfBookDao.updateStatus(inventoryId, CopyOfBookStatus.WRITTEN_OFF.ordinal() + 1);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addCopiesOfBook(String bookIsbn, int copiesCount) throws ServiceException {
        CopyOfBookDao copyOfBookDao = MySqlDaoFactory.getInstance().getCopyOfBookDao();
        List<CopyOfBook> copiesOfBook = new ArrayList<>();
        for (int i = 0; i < copiesCount; i++) {
            copiesOfBook.add(new CopyOfBook(0, new Date(), bookIsbn, CopyOfBookStatus.AVAILABLE));
        }
        try {
            copyOfBookDao.saveAll(copiesOfBook);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteCopyOfBook(int inventoryId) throws ServiceException {
        CopyOfBookDao copyOfBookDao = MySqlDaoFactory.getInstance().getCopyOfBookDao();
        try {
            copyOfBookDao.deleteById(inventoryId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
