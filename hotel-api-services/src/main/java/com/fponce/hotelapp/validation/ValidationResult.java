package com.fponce.hotelapp.validation;

public class ValidationResult {

    private final Status result;
    private final String errorMessage;

    ValidationResult(Status result, String errorMessage){
        this.result = result;
        this.errorMessage = errorMessage;
    }

    public Status getResult() {
        return result;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public static ValidationResult ok() {
        return new ValidationResult(Status.OK, null);
    }

    public static ValidationResult failed(String errorMessage) {
        return new ValidationResult(Status.FAILED, errorMessage);
    }

    public enum Status{
        OK,
        FAILED
    }
}
