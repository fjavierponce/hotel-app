package com.hotelapp.persistence;

import java.sql.SQLException;

public interface HotelRepository {

    void createHotel(int id, String name, int category) throws SQLException;
}
