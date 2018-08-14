package com.fponce.hotelapp;

import com.fponce.hotelapp.config.HotelApiServicesConfig;
import com.hotelapp.config.HotelApiPersistenceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
    HotelApiServicesConfig.class,
    HotelApiPersistenceConfig.class
})
public class HotelApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelApplication.class, args);
    }
}
