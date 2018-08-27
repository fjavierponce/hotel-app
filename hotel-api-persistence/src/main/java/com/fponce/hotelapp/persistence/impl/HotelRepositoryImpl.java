package com.fponce.hotelapp.persistence.impl;

import com.fponce.hotelapp.persistence.HotelRepository;
import com.hotelapp.hotelapp.model.Hotel;
import org.httprpc.sql.Parameters;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

@Repository
class HotelRepositoryImpl implements HotelRepository {

    private DataSource dataSource;

    HotelRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void createHotel(UUID id, String name, int category) throws SQLException {
        Connection connection = dataSource.getConnection();
        String insertHotelSqlCommand = "INSERT INTO HOTEL (ID, NAME, CATEGORY) VALUES (:id, :name, :category)";
        Parameters sqlWithParameters = Parameters.parse(insertHotelSqlCommand);
        PreparedStatement preparedStatement = connection.prepareStatement(sqlWithParameters.getSQL());
        sqlWithParameters.put("id", id);
        sqlWithParameters.put("name", name);
        sqlWithParameters.put("category", category);
        sqlWithParameters.apply(preparedStatement);
        preparedStatement.executeUpdate();
    }

    public Optional<Hotel> getHotel(String hotelName) throws SQLException {
        String selectHotelByNameQuery = "SELECT * FROM HOTEL WHERE NAME = :name";
        Parameters sqlWithParameters = Parameters.parse(selectHotelByNameQuery);
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlWithParameters.getSQL());
        sqlWithParameters.put("name", hotelName);
        sqlWithParameters.apply(preparedStatement);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String name = resultSet.getString("NAME");
            UUID id = UUID.fromString(resultSet.getString("ID"));
            int category = resultSet.getInt("CATEGORY");
            Hotel hotel = new Hotel.Builder(name).category(category).id(id).build();
            return Optional.of(hotel);
        }
        return Optional.empty();
    }
}
