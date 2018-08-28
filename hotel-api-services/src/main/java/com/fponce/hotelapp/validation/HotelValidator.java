package com.fponce.hotelapp.validation;

import com.hotelapp.hotelapp.model.Hotel;
import org.springframework.stereotype.Component;

@Component
public class HotelValidator implements Validator<Hotel> {

    @Override
    public ValidationResult validate(Hotel hotel) {
        if (hotel.getName() == null || hotel.getName().isEmpty())
            return ValidationResult.failed("Hotel name must not be empty.");
        return ValidationResult.ok();
    }
}
