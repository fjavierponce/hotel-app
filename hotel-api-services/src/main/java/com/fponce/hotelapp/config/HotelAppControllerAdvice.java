package com.fponce.hotelapp.config;

import com.fponce.hotelapp.exception.HotelAppServicesException;
import com.hotelapp.hotelapp.model.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
class HotelAppControllerAdvice {

    @ExceptionHandler(HotelAppServicesException.class)
    public ResponseEntity<ErrorResponse> handleGlobalError(HotelAppServicesException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return createError(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponse> createError(final ErrorResponse body, final HttpStatus status) {
        return ResponseEntity.status(status).body(body);
    }

}
