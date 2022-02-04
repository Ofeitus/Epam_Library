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
}
