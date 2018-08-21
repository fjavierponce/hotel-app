package com.fponce.hotelapp.hotel.service;

import com.fponce.hotelapp.exception.HotelAppServicesException;
import com.hotelapp.hotelapp.model.Hotel;

/**
 * Represents a service class to manage hotel entities. This class allows to support hotels CRUD
 * operations with business logic and rules.
 * @author fponce
 */
public interface HotelService {

    /**
     * Validates and manages creation of a Hotel.
     * @param hotel the hotel model that holds parameters.
     * @throws HotelAppServicesException if there occurs an error calling repository.
     */
    void createHotel(Hotel hotel) throws HotelAppServicesException;
}
