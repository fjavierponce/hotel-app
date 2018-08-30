package com.fponce.hotelapp.mapper;

import com.hotelapp.hotelapp.model.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.function.Function;

@Component
public class HotelMapper implements Function<ResultSet, Hotel> {

    private Logger logger = LoggerFactory.getLogger(HotelMapper.class);
    /**
     * Applies this function to the given argument.
     *
     * @param resultSet the function argument
     * @return the function result
     */
    @Override
    public Hotel apply(ResultSet resultSet) {
        if(resultSet == null) return null;
        try {
            return new Hotel.Builder(resultSet.getString("NAME"))
                .category(resultSet.getInt("CATEGORY"))
                .id(UUID.fromString(resultSet.getString("ID")))
                .build();
        } catch (SQLException e) {
            logger.error("Could not convert ResultSet to Hotel model.");
            return null;
        }
    }
}
