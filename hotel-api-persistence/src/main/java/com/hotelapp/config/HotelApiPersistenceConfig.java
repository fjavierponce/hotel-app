package com.hotelapp.config;

import com.hotelapp.persistence.HotelRepository;
import com.hotelapp.persistence.impl.HotelRepositoryImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class HotelApiPersistenceConfig {

    @Bean
    public HotelRepository hotelRepository() {
        return new HotelRepositoryImpl();
    }
}
