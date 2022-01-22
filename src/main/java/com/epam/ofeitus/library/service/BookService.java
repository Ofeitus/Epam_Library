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

public interface BookService {
    List<BookDto> getBooksDtoBySearchRequest(String searchRequest, String category, String authorName, String authorSurname, int yearFrom, int yearTo, int page, int itemsOnPage) throws ServiceException;

    int countBooksBySearchRequest(String searchRequest, String category, String authorName, String authorSurname, int yearFrom, int yearTo) throws ServiceException;

    BookDto getBookDtoByIsbn(String bookIsbn) throws ServiceException;

    int getCopiesCount(String bookIsbn) throws ServiceException;

    int getAvailableCopiesCount(String bookIsbn) throws ServiceException;

    List<BookCategory> getBookCategories() throws ServiceException;

    boolean updateBook(String bookIsbn, String title, String category, int publicationYear, String language, String keyWords, List<String> authorNames, List<String> authorSurnames) throws ServiceException;

    boolean saveBook(String bookIsbn, String title, String category, int publicationYear, String language, String keyWords, List<String> authorNames, List<String> authorSurnames) throws ServiceException;

    void deleteBook(String bookIsbn) throws ServiceException;

    List<CopyOfBookDto> getAllCopiesOfBooks(int page, int itemsOnPage) throws ServiceException;

    int countAllCopiesOfBooks() throws ServiceException;

    List<CopyOfBookDto> getCopiesOfBooksBySearchRequest(String bookIsbn, int inventoryId, int statusId, int page, int itemsOnPage) throws ServiceException;

    int countCopiesOfBooksBySearchRequest(String bookIsbn, int inventoryId, int statusId) throws ServiceException;

    void writeOffCopiesOfBooks(int fromInventoryId, int toInventoryId) throws ServiceException;

    void addCopiesOfBook(String bookIsbn, BigDecimal price, int copiesCount) throws ServiceException;

    void deleteCopyOfBook(int inventoryId) throws ServiceException;

    CopyOfBook getCopyByInventoryId(int inventoryId) throws ServiceException;

    BooksStockReport getBooksReport(Date fromDate, Date toDate) throws ServiceException;

    IssueReport getIssueReport(Date fromDate, Date toDate) throws ServiceException;
}
