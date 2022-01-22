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
import com.epam.ofeitus.library.entity.order.Loan;
import com.epam.ofeitus.library.entity.order.Reservation;
import com.epam.ofeitus.library.entity.order.constiuent.LoanStatus;
import com.epam.ofeitus.library.entity.order.constiuent.ReservationStatus;
import com.epam.ofeitus.library.entity.report.BooksStockReport;
import com.epam.ofeitus.library.entity.report.IssueReport;
import com.epam.ofeitus.library.service.BookService;
import com.epam.ofeitus.library.service.exception.ServiceException;
import com.epam.ofeitus.library.service.validator.EntityValidator;
import com.epam.ofeitus.library.service.validator.ValidatorFactory;

import java.math.BigDecimal;
import java.util.*;

public class BookServiceImpl implements BookService {
    @Override
    public boolean saveBook(String bookIsbn, String title, String category, int publicationYear, String language, String keyWords, List<String> authorNames, List<String> authorSurnames) throws ServiceException {
        DaoFactory daoFactory = MySqlDaoFactory.getInstance();
        BookDao bookDao = daoFactory.getBookDao();
        BookCategoryDao bookCategoryDao = daoFactory.getBookCategoryDao();
        AuthorDao authorDao = daoFactory.getAuthorDao();

        EntityValidator<Book> bookValidator = ValidatorFactory.getInstance().getBookValidator();
        EntityValidator<Author> authorValidator = ValidatorFactory.getInstance().getAuthorValidator();

        try {
            List<Author> authors = new ArrayList<>();
            for (int i = 0; i < authorNames.size(); i++) {
                Author author = new Author(0, authorNames.get(i), authorSurnames.get(i));

                if (!authorValidator.validate(author)) {
                    return false;
                }
                authors.add(new Author(0, authorNames.get(i), authorSurnames.get(i)));
            }

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

            Book book = new Book(bookIsbn, title, publicationYear, categoryId, language, keyWords);

            if (!bookValidator.validate(book)) {
                return false;
            }

            return bookDao.save(book, authors) == 1;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateBook(String bookIsbn, String title, String category, int publicationYear, String language, String keyWords, List<String> authorNames, List<String> authorSurnames) throws ServiceException {
        DaoFactory daoFactory = MySqlDaoFactory.getInstance();
        BookDao bookDao = daoFactory.getBookDao();
        BookCategoryDao bookCategoryDao = daoFactory.getBookCategoryDao();
        AuthorDao authorDao = daoFactory.getAuthorDao();

        EntityValidator<Book> bookValidator = ValidatorFactory.getInstance().getBookValidator();
        EntityValidator<Author> authorValidator = ValidatorFactory.getInstance().getAuthorValidator();

        try {
            List<Author> authors = new ArrayList<>();
            for (int i = 0; i < authorNames.size(); i++) {
                Author author = new Author(0, authorNames.get(i), authorSurnames.get(i));

                if (!authorValidator.validate(author)) {
                    return false;
                }

                authors.add(author);
            }

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

            Book book = new Book(bookIsbn, title, publicationYear, categoryId, language, keyWords);

            if (!bookValidator.validate(book)) {
                return false;
            }

            return bookDao.update(book, authors) == 1;
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
    public List<BookDto> getBooksDtoBySearchRequest(String searchRequest, String category, String authorName, String authorSurname, int yearFrom, int yearTo, int page, int itemsOnPage) throws ServiceException {
        DaoFactory daoFactory = MySqlDaoFactory.getInstance();
        BookDao bookDao = daoFactory.getBookDao();
        AuthorDao authorDao = daoFactory.getAuthorDao();
        BookCategoryDao bookCategoryDao = daoFactory.getBookCategoryDao();

        try {
            Author author = authorDao.findByName(authorName, authorSurname);
            int authorId = author != null ? author.getAuthorId() : 0;

            BookCategory bookCategory = bookCategoryDao.findByName(category);
            int categoryId = bookCategory != null ? bookCategory.getCategoryId() : 0;

            int offset = (page - 1) * itemsOnPage;

            List<Book> books = bookDao.findBySearchRequest(searchRequest, categoryId, authorId, yearFrom, yearTo, offset, itemsOnPage);
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
    public int countBooksBySearchRequest(String searchRequest, String category, String authorName, String authorSurname, int yearFrom, int yearTo) throws ServiceException {
        DaoFactory daoFactory = MySqlDaoFactory.getInstance();
        BookDao bookDao = daoFactory.getBookDao();
        AuthorDao authorDao = daoFactory.getAuthorDao();
        BookCategoryDao bookCategoryDao = daoFactory.getBookCategoryDao();

        try {
            Author author = authorDao.findByName(authorName, authorSurname);
            int authorId = author != null ? author.getAuthorId() : 0;

            BookCategory bookCategory = bookCategoryDao.findByName(category);
            int categoryId = bookCategory != null ? bookCategory.getCategoryId() : 0;

            return bookDao.countBySearchRequest(searchRequest, categoryId, authorId, yearFrom, yearTo);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addCopiesOfBook(String bookIsbn, BigDecimal price, int copiesCount) throws ServiceException {
        CopyOfBookDao copyOfBookDao = MySqlDaoFactory.getInstance().getCopyOfBookDao();

        List<CopyOfBook> copiesOfBook = new ArrayList<>();
        for (int i = 0; i < copiesCount; i++) {
            copiesOfBook.add(new CopyOfBook(0, new Date(), price, bookIsbn, CopyOfBookStatus.AVAILABLE));
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

    @Override
    public void writeOffCopiesOfBooks(int fromInventoryId, int toInventoryId) throws ServiceException {
        CopyOfBookDao copyOfBookDao = MySqlDaoFactory.getInstance().getCopyOfBookDao();

        try {
            // TODO Write of through transaction
            if (fromInventoryId != 0 && toInventoryId == 0) {
                copyOfBookDao.updateStatus(fromInventoryId, CopyOfBookStatus.WRITTEN_OFF.ordinal() + 1);
            } else if (fromInventoryId == 0 && toInventoryId != 0) {
                copyOfBookDao.updateStatus(toInventoryId, CopyOfBookStatus.WRITTEN_OFF.ordinal() + 1);
            } else if (fromInventoryId != 0 && toInventoryId != 0 && fromInventoryId <= toInventoryId) {
                for (int i = fromInventoryId; i <= toInventoryId; i++) {
                    copyOfBookDao.updateStatus(i, CopyOfBookStatus.WRITTEN_OFF.ordinal() + 1);
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public CopyOfBook getCopyOfBookByInventoryId(int inventoryId) throws ServiceException {
        CopyOfBookDao copyOfBookDao = MySqlDaoFactory.getInstance().getCopyOfBookDao();

        try {
            return copyOfBookDao.findById(inventoryId);
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
    public List<CopyOfBookDto> getAllCopiesOfBooks(int page, int itemsOnPage) throws ServiceException {
        DaoFactory daoFactory = MySqlDaoFactory.getInstance();
        CopyOfBookDao copyOfBookDao = daoFactory.getCopyOfBookDao();
        ReservationDao reservationDao = daoFactory.getReservationDao();
        LoanDao loanDao = daoFactory.getLoanDao();

        try {
            int offset = (page - 1) * itemsOnPage;

            List<CopyOfBook> copiesOfBooks = copyOfBookDao.findAllExisting(offset, itemsOnPage);
            List<CopyOfBookDto> copiesOfBooksDto = new ArrayList<>();
            for (CopyOfBook copyOfBook : copiesOfBooks) {
                int userId = 0;
                BookDto bookDto = getBookDtoByIsbn(copyOfBook.getBookIsbn());
                List<Reservation> reservations = reservationDao.findByInventoryId(copyOfBook.getInventoryId());
                Optional<Reservation> activeReservation = reservations.stream().filter(x -> x.getReservationStatus() != ReservationStatus.ISSUED).findFirst();
                if (activeReservation.isPresent()) {
                    userId = activeReservation.get().getUserId();
                }
                List<Loan> loans = loanDao.findByInventoryId(copyOfBook.getInventoryId());
                Optional<Loan> activeLoan = loans.stream().filter(x -> x.getLoanStatus() == LoanStatus.ISSUED).findFirst();
                if (activeLoan.isPresent()) {
                    userId = activeLoan.get().getUserId();
                }
                copiesOfBooksDto.add(new CopyOfBookDto(
                                copyOfBook.getInventoryId(),
                                copyOfBook.getReceiptDate(),
                                copyOfBook.getPrice(),
                                copyOfBook.getBookIsbn(),
                                copyOfBook.getCopyOfBookStatus(),
                                bookDto,
                                userId,
                                reservations.isEmpty() && loans.isEmpty()
                        )
                );
            }
            return copiesOfBooksDto;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int countAllCopiesOfBooks() throws ServiceException {
        CopyOfBookDao copyOfBookDao = MySqlDaoFactory.getInstance().getCopyOfBookDao();

        try {
            return copyOfBookDao.countAllExisting(new Date());
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<CopyOfBookDto> getCopiesOfBooksBySearchRequest(String bookIsbn, int inventoryId, int statusId, int page, int itemsOnPage) throws ServiceException {
        DaoFactory daoFactory = MySqlDaoFactory.getInstance();
        CopyOfBookDao copyOfBookDao = daoFactory.getCopyOfBookDao();
        ReservationDao reservationDao = daoFactory.getReservationDao();
        LoanDao loanDao = daoFactory.getLoanDao();

        try {
            int offset = (page - 1) * itemsOnPage;

            List<CopyOfBook> copiesOfBooks = copyOfBookDao.findBySearchRequest(bookIsbn, inventoryId, statusId, offset, itemsOnPage);
            List<CopyOfBookDto> copiesOfBooksDto = new ArrayList<>();
            for (CopyOfBook copyOfBook : copiesOfBooks) {
                int userId = 0;
                BookDto bookDto = getBookDtoByIsbn(copyOfBook.getBookIsbn());
                List<Reservation> reservations = reservationDao.findByInventoryId(copyOfBook.getInventoryId());
                Optional<Reservation> activeReservation = reservations.stream().filter(x -> x.getReservationStatus() != ReservationStatus.ISSUED).findFirst();
                if (activeReservation.isPresent()) {
                    userId = activeReservation.get().getUserId();
                }
                List<Loan> loans = loanDao.findByInventoryId(copyOfBook.getInventoryId());
                Optional<Loan> activeLoan = loans.stream().filter(x -> x.getLoanStatus() == LoanStatus.ISSUED).findFirst();
                if (activeLoan.isPresent()) {
                    userId = activeLoan.get().getUserId();
                }
                copiesOfBooksDto.add(new CopyOfBookDto(
                        copyOfBook.getInventoryId(),
                        copyOfBook.getReceiptDate(),
                        copyOfBook.getPrice(),
                        copyOfBook.getBookIsbn(),
                        copyOfBook.getCopyOfBookStatus(),
                        bookDto,
                        userId,
                        reservations.isEmpty() && loans.isEmpty())
                );
            }
            return copiesOfBooksDto;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int countCopiesOfBooksBySearchRequest(String bookIsbn, int inventoryId, int statusId) throws ServiceException {
        CopyOfBookDao copyOfBookDao = MySqlDaoFactory.getInstance().getCopyOfBookDao();

        try {
            return copyOfBookDao.countBySearchRequest(bookIsbn, inventoryId, statusId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<BookCategory> getBookCategories() throws ServiceException {
        BookCategoryDao bookCategoryDao = MySqlDaoFactory.getInstance().getBookCategoryDao();

        try {
            return bookCategoryDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public BooksStockReport getBooksReport(Date fromDate, Date toDate) throws ServiceException {
        DaoFactory daoFactory = MySqlDaoFactory.getInstance();
        CopyOfBookDao copyOfBookDao = daoFactory.getCopyOfBookDao();
        BookCategoryDao bookCategoryDao = daoFactory.getBookCategoryDao();

        try {
            BooksStockReport booksStockReport = new BooksStockReport();

            List<Integer> countByCategoryFrom = new ArrayList<>();
            List<Integer> countByCategoryTo = new ArrayList<>();
            List<BigDecimal> priceByCategoryFrom = new ArrayList<>();
            List<BigDecimal> priceByCategoryTo = new ArrayList<>();

            for (BookCategory bookCategory : bookCategoryDao.findAll()) {
                countByCategoryFrom.add(copyOfBookDao.countByCategory(bookCategory, fromDate));
                countByCategoryTo.add(copyOfBookDao.countByCategory(bookCategory, toDate));
                List<CopyOfBook> copiesOfBooks = copyOfBookDao.findByCategory(bookCategory, fromDate);
                BigDecimal price = new BigDecimal("0.00");
                for (CopyOfBook copyOfBook : copiesOfBooks) {
                    price = price.add(copyOfBook.getPrice());
                }
                priceByCategoryFrom.add(price);
                copiesOfBooks = copyOfBookDao.findByCategory(bookCategory, toDate);
                price = new BigDecimal("0.00");
                for (CopyOfBook copyOfBook : copiesOfBooks) {
                    price = price.add(copyOfBook.getPrice());
                }
                priceByCategoryTo.add(price);
            }

            BigDecimal totalPriceFrom = new BigDecimal("0.00");
            for (BigDecimal price : priceByCategoryFrom) {
                totalPriceFrom = totalPriceFrom.add(price);
            }

            BigDecimal totalPriceTo = new BigDecimal("0.00");
            for (BigDecimal price : priceByCategoryTo) {
                totalPriceTo = totalPriceTo.add(price);
            }

            booksStockReport.setTotalCountFrom(copyOfBookDao.countAllExisting(fromDate));
            booksStockReport.setTotalCountTo(copyOfBookDao.countAllExisting(toDate));
            booksStockReport.setTotalPriceFrom(totalPriceFrom);
            booksStockReport.setTotalPriceTo(totalPriceTo);
            booksStockReport.setCountByCategoryFrom(countByCategoryFrom);
            booksStockReport.setCountByCategoryTo(countByCategoryTo);
            booksStockReport.setPriceByCategoryFrom(priceByCategoryFrom);
            booksStockReport.setPriceByCategoryTo(priceByCategoryTo);

            return booksStockReport;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public IssueReport getIssueReport(Date fromDate, Date toDate) throws ServiceException {
        DaoFactory daoFactory = MySqlDaoFactory.getInstance();
        CopyOfBookDao copyOfBookDao = daoFactory.getCopyOfBookDao();
        ReservationDao reservationDao = daoFactory.getReservationDao();
        LoanDao loanDao = daoFactory.getLoanDao();

        try {
            IssueReport issueReport = new IssueReport();

            List<Date> dynamicsDates = new ArrayList<>();
            List<Integer> dynamicsValues = new ArrayList<>();

            Calendar before = Calendar.getInstance();
            before.setTime(fromDate);
            before.add(Calendar.MONTH, -1);
            Calendar start = Calendar.getInstance();
            start.setTime(fromDate);
            Calendar end = Calendar.getInstance();
            end.setTime(toDate);

            dynamicsDates.add(fromDate);
            dynamicsValues.add(reservationDao.countByStatusId(3, fromDate) - reservationDao.countByStatusId(3, before.getTime()));
            dynamicsValues.add(loanDao.countAll(fromDate) - loanDao.countAll(before.getTime()) - dynamicsValues.get(dynamicsValues.size() - 1));
            start.add(Calendar.MONTH, 1);

            Date oldDate = fromDate;

            for (Date date = start.getTime(); start.before(end); start.add(Calendar.MONTH, 1), date = start.getTime()) {
                dynamicsDates.add(date);
                dynamicsValues.add(reservationDao.countByStatusId(3, date) - reservationDao.countByStatusId(3, oldDate));
                dynamicsValues.add(loanDao.countAll(date) - loanDao.countAll(oldDate) - dynamicsValues.get(dynamicsValues.size() - 1));
                oldDate = date;
            }

            dynamicsDates.add(new Date());
            dynamicsValues.add(reservationDao.countByStatusId(3, new Date()) - reservationDao.countByStatusId(3, oldDate));
            dynamicsValues.add(loanDao.countAll(new Date()) - loanDao.countAll(oldDate) - dynamicsValues.get(dynamicsValues.size() - 1));

            issueReport.setTotalIssuedFrom(loanDao.countAll(fromDate));
            issueReport.setTotalIssuedTo(loanDao.countAll(toDate));
            issueReport.setTotalIssuedReservedFrom(reservationDao.countByStatusId(3, fromDate));
            issueReport.setTotalIssuedReservedTo(reservationDao.countByStatusId(3, toDate));
            issueReport.setTotalAvailable(copyOfBookDao.countBySearchRequest("", 0, 1));
            issueReport.setTotalReserved(copyOfBookDao.countBySearchRequest("", 0, 3));
            issueReport.setTotalLoaned(copyOfBookDao.countBySearchRequest("", 0, 4));
            issueReport.setDynamicsDates(dynamicsDates);
            issueReport.setDynamicsValues(dynamicsValues);

            return issueReport;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
