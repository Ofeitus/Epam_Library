package com.epam.ofeitus.library.service;

import com.epam.ofeitus.library.entity.book.Author;
import com.epam.ofeitus.library.entity.book.CopyOfBook;
import com.epam.ofeitus.library.entity.book.constituent.BookCategory;
import com.epam.ofeitus.library.entity.dto.BookDto;
import com.epam.ofeitus.library.service.exception.ServiceException;

import java.util.List;

public interface BookService {
    List<BookDto> getAllBooksDto() throws ServiceException;

    List<BookDto> getBooksDtoBySearchRequest(String searchRequest, String category, String authorName, String authorSurname, int yearFrom, int yearTo) throws ServiceException;

    BookDto getBookDtoByIsbn(String bookIsbn) throws ServiceException;

    int getCopiesCount(String bookIsbn) throws ServiceException;

    int getAvailableCopiesCount(String bookIsbn) throws ServiceException;

    List<BookCategory> getBookCategories() throws ServiceException;

    int updateBook(String bookIsbn, String title, String category, int publicationYear, String language, String keyWords, List<Author> authors) throws ServiceException;

    List<CopyOfBook> getAllCopiesOfBooks() throws ServiceException;

    List<CopyOfBook> getCopiesOfBooksBySearchRequest(String bookIsbn, int inventoryId, int statusId) throws ServiceException;

    void writeOffBook(int inventoryId) throws ServiceException;
}
