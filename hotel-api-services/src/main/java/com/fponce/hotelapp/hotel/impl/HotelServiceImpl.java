package com.fponce.hotelapp.hotel.impl;

import com.fponce.hotelapp.hotel.service.HotelService;
import com.hotelapp.persistence.HotelRepository;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.Random;

public class HotelServiceImpl implements HotelService {

    @Inject
    HotelRepository hotelRepository;

    public void createHotel(String name, int category) {
        try {
            hotelRepository.createHotel(new Random().nextInt(), name, category);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }
}
