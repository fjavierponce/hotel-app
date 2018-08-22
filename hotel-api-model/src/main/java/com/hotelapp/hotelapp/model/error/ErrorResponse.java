package com.hotelapp.hotelapp.model.error;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponse {

    private String errorDescription;

    private List<String> errors;

    public ErrorResponse(List<String> errors) {
        this.errors = errors;
    }

    public ErrorResponse(String errorMessage) {
        this.errorDescription = errorMessage;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String getErrorMessage() {
        return errorDescription;
    }

    public void setErrorMessage(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
