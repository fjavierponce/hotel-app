package com.fponce.hotelapp.validation;

public enum ValidationResult {

    OK("Success."),
    EMPTY_HOTEL_NAME("Hotel name must not be empty.");

    private final String errorMessage;

    ValidationResult(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }
}
