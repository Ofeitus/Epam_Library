package com.epam.ofeitus.library.service.validator;

public interface EntityValidator<T> {
    boolean validate(T entity);
}
