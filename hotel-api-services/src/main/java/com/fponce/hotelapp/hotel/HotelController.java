package com.fponce.hotelapp.hotel;

import com.fponce.hotelapp.hotel.service.HotelService;
import com.hotelapp.hotelapp.model.Hotel;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;
import java.sql.SQLException;

@RestController
@RequestMapping(value = "v1/hotels")
class HotelController {

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
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
