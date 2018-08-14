package com.hotelapp.config;

import com.hotelapp.persistence.HotelRepository;
import com.hotelapp.persistence.impl.HotelRepositoryImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class HotelApiPersistenceConfig {

    @Value ("${hotel.app.fponce.database.url}")
    private String hotel_app_url;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(hotel_app_url);
        return dataSource;
    }

    @Bean
    public HotelRepository hotelRepository() {
        return new HotelRepositoryImpl();
    }
}
