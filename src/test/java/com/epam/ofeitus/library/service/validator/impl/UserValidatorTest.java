package com.epam.ofeitus.library.service.validator.impl;

import com.epam.ofeitus.library.entity.user.User;
import com.epam.ofeitus.library.entity.user.constituent.UserRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

class UserValidatorTest {
    UserValidator userValidator = new UserValidator();

    @Test
    void testValidateCorrect() {
        boolean result = userValidator.validate(new User(0, new Date(), "Ivan", "Ivan", "375291234567", "samplemail@email.com", "", UserRole.ADMIN, false));
        Assertions.assertTrue(result);
    }

    @Test
    void testValidateCountryCode() {
        boolean result = userValidator.validate(new User(0, new Date(), "Ivan", "Ivan", "735291234567", "sampleMail@email.com", "", UserRole.ADMIN, false));
        Assertions.assertFalse(result);
    }

    @Test
    void testValidatePhoneNumberLength() {
        boolean result = userValidator.validate(new User(0, new Date(), "Ivan", "Ivan", "3752912345678", "sampleMail@email.com", "", UserRole.ADMIN, false));
        Assertions.assertFalse(result);
    }

    @Test
    void testValidatePhoneNumberForbiddenSymbols() {
        boolean result = userValidator.validate(new User(0, new Date(), "Ivan", "Ivan", "37529123456A", "sampleMail@email.com", "", UserRole.ADMIN, false));
        Assertions.assertFalse(result);
    }

    @Test
    void testValidateEmailNoDot() {
        boolean result = userValidator.validate(new User(0, new Date(), "Ivan", "Ivan", "375291234567", "sampleMail@emailcom", "", UserRole.ADMIN, false));
        Assertions.assertFalse(result);
    }
}
