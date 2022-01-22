package com.epam.ofeitus.library.service.validator;

import com.epam.ofeitus.library.entity.book.Author;
import com.epam.ofeitus.library.entity.book.Book;
import com.epam.ofeitus.library.entity.user.User;
import com.epam.ofeitus.library.service.validator.impl.AuthorValidator;
import com.epam.ofeitus.library.service.validator.impl.BookValidator;
import com.epam.ofeitus.library.service.validator.impl.UserValidator;

public class ValidatorFactory {
    private static final ValidatorFactory instance = new ValidatorFactory();

    private static final EntityValidator<User> userValidator = new UserValidator();
    private static final EntityValidator<Book> bookValidator = new BookValidator();
    private static final EntityValidator<Author> authorValidator = new AuthorValidator();

    private ValidatorFactory() {
    }

    public static ValidatorFactory getInstance() {
        return instance;
    }

    public EntityValidator<User> getUserValidator() {
        return userValidator;
    }

    public EntityValidator<Book> getBookValidator() {
        return bookValidator;
    }

    public EntityValidator<Author> getAuthorValidator() {
        return authorValidator;
    }
}
