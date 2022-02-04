package com.epam.ofeitus.library.service.validator.impl;

import com.epam.ofeitus.library.entity.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserValidatorTest {
    UserValidator userValidator = new UserValidator();

    @Test
    void testValidate() {
        boolean result = userValidator.validate(new User(0, null, "name", "surname", "phoneNumber", "email", null, null, true));
        Assertions.assertEquals(true, result);
    }
}
