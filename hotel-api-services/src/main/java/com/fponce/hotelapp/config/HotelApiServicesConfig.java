package com.fponce.hotelapp.config;

import com.fponce.hotelapp.hotel.service.HotelService;
import com.fponce.hotelapp.hotel.impl.HotelServiceImpl;
import org.springframework.context.annotation.Bean;

public class HotelApiServicesConfig {

    @Bean
    public HotelService hotelService() {
        return new HotelServiceImpl();
    }
}
