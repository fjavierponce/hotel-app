package com.fponce.hotelapp.persistence.impl;

import com.fponce.hotelapp.persistence.HotelRepository;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

@Component
class HotelRepositoryImpl implements HotelRepository {

    private DataSource dataSource;

    HotelRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void createHotel(UUID id, String name, int category) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO HOTEL" +
            " (ID, NAME, CATEGORY) VALUES (?, ?, ?)");
        preparedStatement.setObject(1, id);
        preparedStatement.setString(2, name);
        preparedStatement.setInt(3, category);
        preparedStatement.executeUpdate();
    }
}
