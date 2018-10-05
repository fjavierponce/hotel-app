package com.fponce.hotelapp.hotel;

import com.fponce.hotelapp.exception.HotelAppServicesException;
import com.fponce.hotelapp.hotel.service.HotelService;
import com.hotelapp.hotelapp.model.Hotel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController()
@RequestMapping("/api/hotels")
class HotelController {

    private final Logger logger = LoggerFactory.getLogger(HotelController.class);
    private HotelService hotelService;

    HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity createHotel(@Valid @RequestBody Hotel hotel) throws HotelAppServicesException {
        logger.info("Calling createHotel with {}", hotel);
        hotelService.createHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
