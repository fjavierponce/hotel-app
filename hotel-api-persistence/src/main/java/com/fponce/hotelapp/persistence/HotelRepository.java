package com.fponce.hotelapp.persistence;

import com.hotelapp.hotelapp.model.Hotel;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Represents a repository class to persist Hotel entities. It allows access data from database,
 * and create entities using JDBC.
 */
public interface HotelRepository {

    /**
     * Persists a hotel entity in database.
     * @param id the hotel id
     * @param name the hotel name
     * @param category the hotel category
     * @throws SQLException if there is an error when executing sql query.
     */
    Hotel createHotel(UUID id, String name, int category) throws SQLException;

    /**
     * Returns a Hotel model if it exist in the database, using name in search.
     * @param hotelName the name of the hotel to search
     * @return a hotel model
     */
    Optional<Hotel> getHotel(String hotelName) throws SQLException;

    /**
     * Returns the list of Hotels registered in the database.
     * @return a list of Hotels
     */
    List<Hotel> getHotels() throws SQLException;
}
