package com.fponce.hotelapp.config;

import com.fponce.hotelapp.exception.HotelAppServicesException;
import com.hotelapp.hotelapp.model.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
class HotelAppControllerAdvice {

    @ExceptionHandler(HotelAppServicesException.class)
    @ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
    @ResponseBody
    public ErrorResponse handleGlobalError(HotelAppServicesException e) {
        return new ErrorResponse(e.getMessage());
    }

}
