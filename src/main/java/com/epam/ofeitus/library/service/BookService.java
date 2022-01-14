package com.epam.ofeitus.library.service;

import com.epam.ofeitus.library.entity.book.Author;
import com.epam.ofeitus.library.entity.book.CopyOfBook;
import com.epam.ofeitus.library.entity.book.constituent.BookCategory;
import com.epam.ofeitus.library.entity.dto.BookDto;
import com.epam.ofeitus.library.entity.dto.CopyOfBookDto;
import com.epam.ofeitus.library.service.exception.ServiceException;

import java.util.List;

public interface BookService {
    List<BookDto> getAllBooksDto() throws ServiceException;

    List<BookDto> getBooksDtoBySearchRequest(String searchRequest, String category, String authorName, String authorSurname, int yearFrom, int yearTo, int page, int itemsOnPage) throws ServiceException;

    int countBooksBySearchRequest(String searchRequest, String category, String authorName, String authorSurname, int yearFrom, int yearTo) throws ServiceException;

    BookDto getBookDtoByIsbn(String bookIsbn) throws ServiceException;

    int getCopiesCount(String bookIsbn) throws ServiceException;

    int getAvailableCopiesCount(String bookIsbn) throws ServiceException;

    List<BookCategory> getBookCategories() throws ServiceException;

    int updateBook(String bookIsbn, String title, String category, int publicationYear, String language, String keyWords, List<Author> authors) throws ServiceException;

    int saveBook(String bookIsbn, String title, String category, int publicationYear, String language, String keyWords, List<Author> authors) throws ServiceException;

    void deleteBook(String bookIsbn) throws ServiceException;

    List<CopyOfBookDto> getAllCopiesOfBooks(int page, int itemsOnPage) throws ServiceException;

    int countAllCopiesOfBooks() throws ServiceException;

    List<CopyOfBookDto> getCopiesOfBooksBySearchRequest(String bookIsbn, int inventoryId, int statusId, int page, int itemsOnPage) throws ServiceException;

    int countCopiesOfBooksBySearchRequest(String bookIsbn, int inventoryId, int statusId) throws ServiceException;

    void writeOffCopiesOfBooks(int fromInventoryId, int toInventoryId) throws ServiceException;

    void addCopiesOfBook(String bookIsbn, int copiesCount) throws ServiceException;

    void deleteCopyOfBook(int inventoryId) throws ServiceException;

    CopyOfBook getCopyByInventoryId(int inventoryId) throws ServiceException;
}
