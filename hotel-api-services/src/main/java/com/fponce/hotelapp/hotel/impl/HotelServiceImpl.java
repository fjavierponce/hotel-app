package com.fponce.hotelapp.hotel.impl;

import com.fponce.hotelapp.exception.HotelAppServicesException;
import com.fponce.hotelapp.hotel.service.HotelService;
import com.hotelapp.hotelapp.model.Hotel;
import com.fponce.hotelapp.persistence.HotelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

@Service
class HotelServiceImpl implements HotelService {

    private final Logger logger = LoggerFactory.getLogger(HotelServiceImpl.class);
    HotelRepository hotelRepository;

    HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public void createHotel(Hotel hotel) throws HotelAppServicesException {
        try {
            Optional<Hotel> hotelInDatabase = hotelRepository.getHotel(hotel.getName());
            if(hotelInDatabase.isPresent()) {
                logger.error("Error creating hotel: {} Cause: Hotel already exists.", hotel);
                throw new HotelAppServicesException("Entity already exists");
            }
            hotelRepository.createHotel(UUID.randomUUID(), hotel.getName(), hotel.getCategory());
        } catch (SQLException e) {
            logger.error("Error creating hotel: {} Cause: {}", hotel, e.getMessage());
            throw new HotelAppServicesException("Error creating the hotel.", e);
        }
    }
}
