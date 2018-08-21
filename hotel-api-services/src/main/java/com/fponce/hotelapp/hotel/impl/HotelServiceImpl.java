package com.fponce.hotelapp.hotel.impl;

import com.fponce.hotelapp.exception.HotelAppServicesException;
import com.fponce.hotelapp.hotel.service.HotelService;
import com.hotelapp.hotelapp.model.Hotel;
import com.fponce.hotelapp.persistence.HotelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
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
            hotelRepository.createHotel(UUID.randomUUID(), hotel.getName(), hotel.getCategory());
        } catch (SQLException e) {
            logger.error("Error creating hotel: {}", hotel);
            throw new HotelAppServicesException("Error creating the hotel.", e);
        }
    }
}
