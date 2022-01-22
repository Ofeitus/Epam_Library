package com.epam.ofeitus.library.service.validator.impl;

import com.epam.ofeitus.library.entity.book.Author;
import com.epam.ofeitus.library.service.validator.EntityValidator;
import com.epam.ofeitus.library.service.validator.ValidationPattern;

import java.util.regex.Pattern;

public class AuthorValidator implements EntityValidator<Author> {
    @Override
    public boolean validate(Author author) {
        return author != null &&
                author.getName() != null && Pattern.compile(ValidationPattern.NAME_PATTERN).matcher(author.getName()).matches() &&
                author.getSurname() != null && Pattern.compile(ValidationPattern.NAME_PATTERN).matcher(author.getSurname()).matches();
    }
}
