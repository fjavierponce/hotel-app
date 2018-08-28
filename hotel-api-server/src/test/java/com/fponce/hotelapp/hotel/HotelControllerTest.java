package com.fponce.hotelapp.hotel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fponce.hotelapp.persistence.HotelRepository;
import com.hotelapp.hotelapp.model.Hotel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HotelControllerTest {

    private static final String API_HOTELS_ENDPOINT = "/api/hotelapp/v1/hotels";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HotelRepository hotelRepository;

    @Test
    public void getHotels() throws Exception {
        hotelRepository.createHotel(UUID.randomUUID(), "getHotelsApiTest", 5);
        hotelRepository.createHotel(UUID.randomUUID(), "getHotelsApiTest2", 5);
        List<Hotel> hotels = hotelRepository.getHotels();

        MvcResult result = this.mockMvc
            .perform(get(API_HOTELS_ENDPOINT)).andDo(print())
            .andExpect(status().is2xxSuccessful())
            .andExpect(jsonPath("$", hasSize(hotels.size()))).andReturn();
    }
}
