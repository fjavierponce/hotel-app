package com.fponce.hotelapp.hotel.impl;

import com.fponce.hotelapp.hotel.service.HotelService;
import com.hotelapp.hotelapp.model.Hotel;
import com.hotelapp.persistence.HotelRepository;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.Random;

public class HotelServiceImpl implements HotelService {

    @Inject
    HotelRepository hotelRepository;

    public void createHotel(Hotel hotel) throws SQLException {
            // TODO generate id
            // TODO implement exceptions (read Effective Java exception)
            hotelRepository.createHotel(new Random().nextInt(), hotel.getName(), hotel.getCategory());
    }
}
