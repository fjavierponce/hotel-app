package com.fponce.hotelapp.exception;

public class HotelAppServicesException extends Exception {

    public HotelAppServicesException(String message) {
        super(message);
    }

    public HotelAppServicesException(String message, Throwable cause) {
        super(message, cause);
    }
}
