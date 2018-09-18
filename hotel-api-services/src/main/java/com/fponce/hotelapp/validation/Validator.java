package com.fponce.hotelapp.validation;

@FunctionalInterface
public interface Validator<T> {
    ValidationResult validate(T parameter);
}
