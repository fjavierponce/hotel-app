package com.fponce.hotelapp.hotel.service;

import com.fponce.hotelapp.exception.HotelAppServicesException;
import com.hotelapp.hotelapp.model.Hotel;

public interface HotelService {

    void createHotel(Hotel hotel) throws HotelAppServicesException;
}
