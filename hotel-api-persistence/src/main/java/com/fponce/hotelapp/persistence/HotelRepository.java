package com.fponce.hotelapp.persistence;

import java.sql.SQLException;
import java.util.UUID;

public interface HotelRepository {

    void createHotel(UUID id, String name, int category) throws SQLException;
}
