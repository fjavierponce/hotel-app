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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
class HotelRepositoryImpl implements HotelRepository {

    private DataSource dataSource;

    HotelRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Optional<Hotel> createHotel(UUID id, String name, int category) throws SQLException {
        Connection connection = dataSource.getConnection();
        String insertHotelSqlCommand = "INSERT INTO HOTEL (ID, NAME, CATEGORY) VALUES (:id, :name, :category)";
        Parameters sqlWithParameters = Parameters.parse(insertHotelSqlCommand);
        PreparedStatement preparedStatement = connection.prepareStatement(sqlWithParameters.getSQL());
        sqlWithParameters.put("id", id);
        sqlWithParameters.put("name", name);
        sqlWithParameters.put("category", category);
        sqlWithParameters.apply(preparedStatement);
        int rowsUpdated = preparedStatement.executeUpdate();
        if(rowsUpdated > 0) {
            Hotel registeredHotel = new Hotel.Builder(name).id(id).category(category).build();
            return Optional.of(registeredHotel);
        } else {
            return Optional.empty();
        }
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

    @Override
    public List<Hotel> getHotels() throws SQLException {
        Connection connection = dataSource.getConnection();
        String getHotelsQuery = "SELECT * FROM HOTEL";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(getHotelsQuery);
        List<Hotel> hotels = new ArrayList<>();
        while(resultSet.next()){
            Hotel hotelToAdd = new Hotel.Builder(resultSet.getString("NAME"))
                    .category(resultSet.getInt("CATEGORY"))
                    .id(UUID.fromString(resultSet.getString("ID")))
                    .build();
            hotels.add(hotelToAdd);
        }
        return hotels;
    }
}
