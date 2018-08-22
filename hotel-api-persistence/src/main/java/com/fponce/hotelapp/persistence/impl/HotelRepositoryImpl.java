package com.fponce.hotelapp.persistence.impl;

import com.fponce.hotelapp.persistence.HotelRepository;
import org.httprpc.sql.Parameters;
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
}
