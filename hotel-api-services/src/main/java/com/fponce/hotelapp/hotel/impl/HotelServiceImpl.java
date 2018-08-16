package com.fponce.hotelapp.hotel.impl;

import com.fponce.hotelapp.hotel.service.HotelService;
import com.hotelapp.hotelapp.model.Hotel;
import com.fponce.hotelapp.persistence.HotelRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.UUID;

@Service
class HotelServiceImpl implements HotelService {

    HotelRepository hotelRepository;

    HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public void createHotel(Hotel hotel) throws SQLException {
            hotelRepository.createHotel(UUID.randomUUID(), hotel.getName(), hotel.getCategory());
    }
}
