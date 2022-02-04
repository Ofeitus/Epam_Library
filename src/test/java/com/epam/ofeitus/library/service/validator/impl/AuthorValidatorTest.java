package com.epam.ofeitus.library.service.validator.impl;

import com.epam.ofeitus.library.entity.book.Author;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AuthorValidatorTest {
    AuthorValidator authorValidator = new AuthorValidator();

    @Test
    void testValidateCorrect() {
        boolean result = authorValidator.validate(new Author(0, "Иван", "Ivan"));
        Assertions.assertTrue(result);
    }

    @Test
    void testValidateForbiddenSymbols() {
        boolean result = authorValidator.validate(new Author(0, "Иван?", "Ivan"));
        Assertions.assertFalse(result);
    }

    @Test
    void testValidateOneLanguage() {
        boolean result = authorValidator.validate(new Author(0, "Iван", "Ivan"));
        Assertions.assertFalse(result);
    }

    @Test
    void testValidateCapitalLetter() {
        boolean result = authorValidator.validate(new Author(0, "ivan", "Ivan"));
        Assertions.assertFalse(result);
    }

    @Test
    void testValidateLength() {
        boolean result = authorValidator.validate(new Author(0, "I", "Ivan"));
        Assertions.assertFalse(result);
    }
}
