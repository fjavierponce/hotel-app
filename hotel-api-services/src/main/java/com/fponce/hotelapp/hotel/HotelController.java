package com.fponce.hotelapp.hotel;

import com.fponce.hotelapp.exception.HotelAppServicesException;
import com.fponce.hotelapp.hotel.service.HotelService;
import com.hotelapp.hotelapp.model.Hotel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("v1/hotels")
class HotelController {

    private final Logger logger = LoggerFactory.getLogger(HotelController.class);
    private HotelService hotelService;

    HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public Resource<Hotel> createHotel(@RequestBody Hotel hotel) throws HotelAppServicesException {
        logger.info("Calling createHotel with {}", hotel);
        Hotel successfullyRegisteredHotel = hotelService.createHotel(hotel);
        return new Resource(successfullyRegisteredHotel);
    }

    @GetMapping
    public Resources<Hotel> getHotels() throws HotelAppServicesException {
        logger.info("Calling getHotels.");
        List<Hotel> hotels = hotelService.getHotels();
        return new Resources<>(hotels);
    }
}
