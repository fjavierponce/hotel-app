package com.fponce.hotelapp.hotel.impl;

import com.fponce.hotelapp.exception.HotelAppServicesException;
import com.fponce.hotelapp.hotel.service.HotelService;
import com.fponce.hotelapp.validation.HotelValidator;
import com.fponce.hotelapp.validation.ValidationResult;
import com.fponce.hotelapp.validation.Validator;
import com.hotelapp.hotelapp.model.Hotel;
import com.fponce.hotelapp.persistence.HotelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
class HotelServiceImpl implements HotelService {

    private final Logger logger = LoggerFactory.getLogger(HotelServiceImpl.class);
    HotelRepository hotelRepository;
    HotelValidator hotelValidator;

    HotelServiceImpl(HotelRepository hotelRepository, HotelValidator hotelValidator) {
        this.hotelRepository = hotelRepository;
        this.hotelValidator = hotelValidator;
    }

    public Hotel createHotel(Hotel hotel) throws HotelAppServicesException {
        try {
            validateHotel(hotel);
            Optional<Hotel> hotelInDatabase = hotelRepository.getHotel(hotel.getName());
            if(hotelInDatabase.isPresent()) {
                logger.error("Error creating hotel: {} Cause: Hotel already exists.", hotel);
                throw new HotelAppServicesException("Entity already exists");
            }
            Optional<Hotel> createdHotel = hotelRepository.createHotel(UUID.randomUUID(), hotel.getName(), hotel.getCategory());
            if (createdHotel.isPresent()) {
                return createdHotel.get();
            } else {
                throw new HotelAppServicesException("Error creating the hotel.");
            }
        } catch (SQLException e) {
            logger.error("Error creating hotel: {} Cause: {}", hotel, e.getMessage());
            throw new HotelAppServicesException("Error creating the hotel.", e);
        }
    }

    @Override
    public List<Hotel> getHotels() throws HotelAppServicesException {
        try {
            return hotelRepository.getHotels();
        } catch (SQLException e) {
            logger.error("Error calling get Hotels. Cause: {}", e.getMessage());
            throw new HotelAppServicesException("Error fetching hotels.", e);
        }
    }

    private void validateHotel(Hotel hotel) throws HotelAppServicesException {
        ValidationResult validationResult = hotelValidator.validate(hotel);
        if (!ValidationResult.OK.equals(validationResult)) {
            throw new HotelAppServicesException(validationResult.getErrorMessage());
        }
    }
}
