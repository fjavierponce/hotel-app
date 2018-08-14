package com.fponce.hotelapp.hotel;

import com.fponce.hotelapp.hotel.service.HotelService;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping(value = "v1/hotels")
public class HotelController {

    @Inject
    private HotelService hotelService;

    @GetMapping
    public String createHotel(@RequestParam String name, @RequestParam int category) {
        hotelService.createHotel(name, category);
        return "success";
    }
}
