package com.hotelapp.persistence.impl;

import com.hotelapp.persistence.HotelRepository;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class HotelRepositoryImpl implements HotelRepository {

    @Inject
    DataSource postgresDataSource;

    public void createHotel(int id, String name, int category) throws SQLException {
        Connection connection = postgresDataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO HOTEL" +
            " (ID, NAME, CATEGORY) VALUES (?, ?, ?)");
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, name);
        preparedStatement.setInt(3, category);
        preparedStatement.executeUpdate();
    }
}