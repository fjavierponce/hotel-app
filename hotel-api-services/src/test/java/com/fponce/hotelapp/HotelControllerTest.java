package com.fponce.hotelapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fponce.hotelapp.persistence.HotelRepository;
import com.hotelapp.hotelapp.model.Hotel;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public void createHotelWithNoDuplications() throws Exception {
        String hotelName = "HotelTestCreation";
        int category = 5;
        Hotel hotelToCreate = new Hotel.Builder(hotelName).category(category).build();

        this.mockMvc
            .perform(
                MockMvcRequestBuilders.post(API_HOTELS_ENDPOINT)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(hotelToCreate))
            ).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        this.mockMvc
            .perform(
                MockMvcRequestBuilders.post(API_HOTELS_ENDPOINT)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(hotelToCreate))
            ).andExpect(MockMvcResultMatchers.status().isPreconditionFailed()).andReturn();

        Optional<Hotel> hotelFromDatabase = hotelRepository.getHotel(hotelName);
        Assertions.assertThat(hotelFromDatabase).isNotEmpty();
        Assertions.assertThat(hotelFromDatabase.get().getName()).isEqualTo(hotelName);
        Assertions.assertThat(hotelFromDatabase.get().getCategory()).isEqualTo(category);
    }

    @Test
    public void createHotelWithInvalidParameters() throws Exception {
        Hotel hotelWithInvalidParameters = new Hotel.Builder("").build();
        this.mockMvc
            .perform(
                MockMvcRequestBuilders.post(API_HOTELS_ENDPOINT)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(hotelWithInvalidParameters))
            ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isPreconditionFailed()).andReturn();
    }

    @Test
    public void getHotels() throws Exception {
        hotelRepository.createHotel(UUID.randomUUID(), "hotelCreationTest1", 5);
        hotelRepository.createHotel(UUID.randomUUID(), "hotelCreationTest2", 5);
        List<Hotel> hotels = hotelRepository.getHotels();
        this.mockMvc
                .perform(MockMvcRequestBuilders.get(API_HOTELS_ENDPOINT))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*]", Matchers.notNullValue()))
                .andReturn();
    }
}
