package com.epam.ofeitus.library.service.validator.impl;

import com.epam.ofeitus.library.entity.book.Book;
import com.epam.ofeitus.library.service.validator.EntityValidator;
import com.epam.ofeitus.library.service.validator.ValidationPattern;

import java.util.regex.Pattern;

public class BookValidator implements EntityValidator<Book> {
    @Override
    public boolean validate(Book book) {
        return book != null &&
                book.getIsbn() != null &&
                Pattern.compile(ValidationPattern.ISBN_PATTERN).matcher(book.getIsbn()).matches() &&
                book.getTitle() != null &&
                book.getTitle().length() > 0 && book.getTitle().length() <= 100 &&
                book.getPublicationYear() >= 0 &&
                book.getLanguage() != null &&
                Pattern.compile(ValidationPattern.NAME_PATTERN).matcher(book.getLanguage()).matches() &&
                book.getKeyWords() != null;
    }
}
