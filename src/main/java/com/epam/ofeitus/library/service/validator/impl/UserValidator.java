package com.epam.ofeitus.library.service.validator.impl;

import com.epam.ofeitus.library.entity.user.User;
import com.epam.ofeitus.library.service.validator.EntityValidator;
import com.epam.ofeitus.library.service.validator.ValidationPattern;

import java.util.regex.Pattern;

public class UserValidator implements EntityValidator<User> {
    @Override
    public boolean validate(User user) {
        return user != null &&
                user.getName() != null &&
                Pattern.compile(ValidationPattern.NAME_PATTERN).matcher(user.getName()).matches() &&
                user.getSurname() != null &&
                Pattern.compile(ValidationPattern.NAME_PATTERN).matcher(user.getSurname()).matches() &&
                user.getPhoneNumber() != null &&
                Pattern.compile(ValidationPattern.PHONE_PATTERN).matcher(user.getPhoneNumber()).matches() &&
                user.getEmail() != null &&
                Pattern.compile(ValidationPattern.EMAIL_PATTERN).matcher(user.getEmail()).matches();
    }
}
