package com.fponce.hotelapp.hotel;

import com.fponce.hotelapp.hotel.service.HotelService;
import com.hotelapp.hotelapp.model.Hotel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.sql.SQLException;

@RestController("v1/hotels")
class HotelController {

    private final Logger logger = LoggerFactory.getLogger(HotelController.class);
    private HotelService hotelService;

    HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping
    public ResponseEntity createHotel(@Valid @RequestBody Hotel hotel) {
        try {
            hotelService.createHotel(hotel);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (SQLException e) {
            logger.error("createHotel endpoint failed with hotel name={}", hotel.getName());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
