package com.fponce.hotelapp.hotel.impl;

import com.fponce.hotelapp.hotel.service.HotelService;
import com.hotelapp.hotelapp.model.Hotel;
import com.hotelapp.persistence.HotelRepository;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.Random;
import java.util.UUID;

public class HotelServiceImpl implements HotelService {

    @Inject
    HotelRepository hotelRepository;

    public void createHotel(Hotel hotel) throws SQLException {
            hotelRepository.createHotel(UUID.randomUUID(), hotel.getName(), hotel.getCategory());
    }
}
