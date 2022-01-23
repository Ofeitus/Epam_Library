package com.epam.ofeitus.library.service;

import com.epam.ofeitus.library.entity.book.CopyOfBook;
import com.epam.ofeitus.library.entity.book.constituent.BookCategory;
import com.epam.ofeitus.library.entity.dto.BookDto;
import com.epam.ofeitus.library.entity.dto.CopyOfBookDto;
import com.epam.ofeitus.library.entity.report.BooksStockReport;
import com.epam.ofeitus.library.entity.report.IssueReport;
import com.epam.ofeitus.library.service.exception.ServiceException;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Book service interface.
 */
public interface BookService {
    /**
     * Save book to data source.
     *
     * @param bookIsbn        book isbn
     * @param title           book title
     * @param category        book category
     * @param publicationYear book publication year
     * @param language        book language
     * @param keyWords        book key words
     * @param authorNames     book authors names
     * @param authorSurnames  book authors surnames
     * @return if book successfully saved
     * @throws ServiceException thrown when dao exception occurs
     */
    boolean saveBook(String bookIsbn, String title, String category, int publicationYear, String language, String keyWords, List<String> authorNames, List<String> authorSurnames) throws ServiceException;

    /**
     * Update book data.
     *
     * @param bookIsbn        book isbn
     * @param title           book title
     * @param category        book category
     * @param publicationYear book publication year
     * @param language        book language
     * @param keyWords        book key words
     * @param authorNames     book authors names
     * @param authorSurnames  book authors surnames
     * @return if book successfully updated
     * @throws ServiceException thrown when dao exception occurs
     */
    boolean updateBook(String bookIsbn, String title, String category, int publicationYear, String language, String keyWords, List<String> authorNames, List<String> authorSurnames) throws ServiceException;

    /**
     * Delete book from data source.
     *
     * @param bookIsbn book isbn
     * @throws ServiceException thrown when dao exception occurs
     */
    void deleteBook(String bookIsbn) throws ServiceException;

    /**
     * Gets book dto by isbn.
     *
     * @param bookIsbn book isbn
     * @return the book dto with given isbn
     * @throws ServiceException thrown when dao exception occurs
     */
    BookDto getBookDtoByIsbn(String bookIsbn) throws ServiceException;

    /**
     * Gets books dto matching the search request on given page.
     *
     * @param searchRequest book title or isbn or key word
     * @param category      book category
     * @param authorName    book author name
     * @param authorSurname book author surname
     * @param yearFrom      book year from
     * @param yearTo        book year to
     * @param page          page
     * @param itemsOnPage   items on page
     * @return list of books dto
     * @throws ServiceException thrown when dao exception occurs
     */
    List<BookDto> getBooksDtoBySearchRequest(String searchRequest, String category, String authorName, String authorSurname, int yearFrom, int yearTo, int page, int itemsOnPage) throws ServiceException;

    /**
     * Gets count of books matching the search request on given page.
     *
     * @param searchRequest book title or isbn or key word
     * @param category      book category
     * @param authorName    book author name
     * @param authorSurname book author surname
     * @param yearFrom      book year from
     * @param yearTo        book year to
     * @return count of books
     * @throws ServiceException thrown when dao exception occurs
     */
    int countBooksBySearchRequest(String searchRequest, String category, String authorName, String authorSurname, int yearFrom, int yearTo) throws ServiceException;

    /**
     * Save given number of copies of book to data source.
     *
     * @param bookIsbn    book isbn
     * @param price       copy of book price
     * @param copiesCount copies count
     * @throws ServiceException thrown when dao exception occurs
     */
    void addCopiesOfBook(String bookIsbn, BigDecimal price, int copiesCount) throws ServiceException;

    /**
     * Delete copy of book from data source.
     *
     * @param inventoryId copy of book inventory id
     * @throws ServiceException thrown when dao exception occurs
     */
    void deleteCopyOfBook(int inventoryId) throws ServiceException;

    /**
     * Set copies of books status as written off.
     *
     * @param fromInventoryId from inventory id
     * @param toInventoryId   to inventory id
     * @throws ServiceException thrown when dao exception occurs
     */
    void writeOffCopiesOfBooks(int fromInventoryId, int toInventoryId) throws ServiceException;

    /**
     * Gets copy of book by inventory id.
     *
     * @param inventoryId copy of book inventory id
     * @return copy of book
     * @throws ServiceException thrown when dao exception occurs
     */
    CopyOfBook getCopyOfBookByInventoryId(int inventoryId) throws ServiceException;

    /**
     * Gets count of copies of book with given isbn.
     *
     * @param bookIsbn book isbn
     * @return copies count
     * @throws ServiceException thrown when dao exception occurs
     */
    int getCopiesCount(String bookIsbn) throws ServiceException;

    /**
     * Gets count of available copies of book with given isbn.
     *
     * @param bookIsbn book isbn
     * @return available copies count
     * @throws ServiceException thrown when dao exception occurs
     */
    int getAvailableCopiesCount(String bookIsbn) throws ServiceException;

    /**
     * Gets all copies of books on given page.
     *
     * @param page        page
     * @param itemsOnPage items on page
     * @return list of copies of books
     * @throws ServiceException thrown when dao exception occurs
     */
    List<CopyOfBookDto> getAllCopiesOfBooks(int page, int itemsOnPage) throws ServiceException;

    /**
     * Gets count of copies of books on given page.
     *
     * @return count of copies of books
     * @throws ServiceException thrown when dao exception occurs
     */
    int countAllCopiesOfBooks() throws ServiceException;

    /**
     * Gets copies of books Dto matching the search request on given page.
     *
     * @param bookIsbn    book isbn
     * @param inventoryId copy of book inventory id
     * @param statusId    copy of book status id
     * @param page        page
     * @param itemsOnPage items on page
     * @return list copies of books Dto
     * @throws ServiceException thrown when dao exception occurs
     */
    List<CopyOfBookDto> getCopiesOfBooksBySearchRequest(String bookIsbn, int inventoryId, int statusId, int page, int itemsOnPage) throws ServiceException;

    /**
     * Gets count copies of books by search request on given page.
     *
     * @param bookIsbn    book isbn
     * @param inventoryId copy of book inventory id
     * @param statusId    copy of book status id
     * @return count of copies of books
     * @throws ServiceException thrown when dao exception occurs
     */
    int countCopiesOfBooksBySearchRequest(String bookIsbn, int inventoryId, int statusId) throws ServiceException;

    /**
     * Gets book categories.
     *
     * @return list of book categories
     * @throws ServiceException thrown when dao exception occurs
     */
    List<BookCategory> getBookCategories() throws ServiceException;

    /**
     * Gets book stock report.
     *
     * @param fromDate from date
     * @param toDate   to date
     * @return book stock report
     * @throws ServiceException thrown when dao exception occurs
     */
    BooksStockReport getBooksReport(Date fromDate, Date toDate) throws ServiceException;

    /**
     * Gets issue report.
     *
     * @param fromDate from date
     * @param toDate   to date
     * @return issue report
     * @throws ServiceException thrown when dao exception occurs
     */
    IssueReport getIssueReport(Date fromDate, Date toDate) throws ServiceException;
}
