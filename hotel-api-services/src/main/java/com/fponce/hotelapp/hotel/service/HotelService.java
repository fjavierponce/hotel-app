package com.fponce.hotelapp.hotel.service;

import com.hotelapp.hotelapp.model.Hotel;

import java.sql.SQLException;

public interface HotelService {

    void createHotel(Hotel hotel) throws SQLException;
}
