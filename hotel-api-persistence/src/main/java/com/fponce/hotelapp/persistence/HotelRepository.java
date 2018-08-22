package com.fponce.hotelapp.persistence;

import java.sql.SQLException;
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
    void createHotel(UUID id, String name, int category) throws SQLException;
}
