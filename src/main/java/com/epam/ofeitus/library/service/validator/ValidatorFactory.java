package com.epam.ofeitus.library.service.validator;

import com.epam.ofeitus.library.entity.User;
import com.epam.ofeitus.library.service.validator.impl.UserValidator;

public class ValidatorFactory {
    private static final ValidatorFactory instance = new ValidatorFactory();

    private static final EntityValidator<User> userValidator = new UserValidator();

    private ValidatorFactory() {
    }

    public static ValidatorFactory getInstance() {
        return instance;
    }

    public EntityValidator<User> getUserValidator() {
        return userValidator;
    }
}
