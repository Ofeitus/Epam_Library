package com.epam.ofeitus.library.service.validator.impl;

import com.epam.ofeitus.library.entity.book.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BookValidatorTest {
    BookValidator bookValidator = new BookValidator();

    @Test
    void testValidateCorrect() {
        boolean result = bookValidator.validate(new Book("1111111111", "title", 0, 1, "Language", "keyWords"));
        Assertions.assertTrue(result);
    }

    @Test
    void testValidateCorrectLongIsbn() {
        boolean result = bookValidator.validate(new Book("2222222222222", "title", 0, 1, "Язык", "keyWords"));
        Assertions.assertTrue(result);
    }

    @Test
    void testValidateIsbnLength() {
        boolean result = bookValidator.validate(new Book("33333333333", "title", 0, 1, "Language", "keyWords"));
        Assertions.assertFalse(result);
    }

    @Test
    void testValidateIsbnForbiddenSymbols() {
        boolean result = bookValidator.validate(new Book("A111111111", "title", 0, 1, "Language", "keyWords"));
        Assertions.assertFalse(result);
    }
}
