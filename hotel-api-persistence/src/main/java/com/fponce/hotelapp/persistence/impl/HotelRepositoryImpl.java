package com.fponce.hotelapp.persistence.impl;

import com.fponce.hotelapp.persistence.HotelRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

@Repository
class HotelRepositoryImpl implements HotelRepository {

    private DataSource dataSource;

    HotelRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void createHotel(String id, String name, int category) throws SQLException {
        Connection connection = dataSource.getConnection();
        String insertHotelSqlCommand = "INSERT INTO HOTEL (ID, NAME, CATEGORY) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertHotelSqlCommand);
        preparedStatement.setString(1, id);
        preparedStatement.setString(2, name);
        preparedStatement.setInt(3, category);
        preparedStatement.executeUpdate();
    }
}
