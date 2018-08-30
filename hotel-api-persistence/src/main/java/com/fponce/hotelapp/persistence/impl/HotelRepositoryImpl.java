package com.fponce.hotelapp.persistence.impl;

import com.fponce.hotelapp.mapper.HotelMapper;
import com.fponce.hotelapp.persistence.HotelRepository;
import com.fponce.hotelapp.util.ResultSetIterator;
import com.hotelapp.hotelapp.model.Hotel;
import org.httprpc.sql.Parameters;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Spliterators;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.stream.StreamSupport.stream;

@Repository
class HotelRepositoryImpl implements HotelRepository {

    private DataSource dataSource;
    private HotelMapper hotelMapper;

    HotelRepositoryImpl(DataSource dataSource, HotelMapper hotelMapper) {
        this.dataSource = dataSource;
        this.hotelMapper = hotelMapper;
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

    /**
     * Returns the list of Hotels registered in the database. It can use a name
     * to filter the search.
     *
     * @return
     */
    @Override
    public List<Hotel> getHotels() throws SQLException {
        Connection connection = dataSource.getConnection();
        String getHotelsQuery = "SELECT * FROM HOTEL";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(getHotelsQuery);
        Stream<ResultSet> stream = StreamSupport.stream(Spliterators.spliteratorUnknownSize(new ResultSetIterator(resultSet), 0), false);
        return stream.map(hotelMapper)
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

}
