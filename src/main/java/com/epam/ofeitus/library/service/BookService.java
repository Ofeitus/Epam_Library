package com.epam.ofeitus.library.service;

import com.epam.ofeitus.library.entity.dto.BookDto;
import com.epam.ofeitus.library.service.exception.ServiceException;

import java.util.List;

public interface BookService {
    List<BookDto> getAllBooksDto() throws ServiceException;
}
