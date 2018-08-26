package com.fponce.hotelapp.hotel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fponce.hotelapp.persistence.HotelRepository;
import com.hotelapp.hotelapp.model.Hotel;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class HotelControllerTest {

    private static final String API_HOTELS_ENDPOINT = "/api/hotelapp/v1/hotels";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HotelRepository hotelRepository;

    public void createHotelWithNoDuplications() throws Exception {
        String hotelName = "hotel-test";
        int category = 5;
        Hotel hotelToCreate = new Hotel();
        hotelToCreate.setName(hotelName);
        hotelToCreate.setCategory(category);

         MvcResult result = this.mockMvc
            .perform(
                post(API_HOTELS_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(hotelToCreate))
            ).andDo(print())
            .andExpect(status().isCreated()).andReturn();

         MvcResult secondResult = this.mockMvc
            .perform(
                post(API_HOTELS_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(hotelToCreate))
            ).andDo(print())
            .andExpect(status().isPreconditionFailed()).andReturn();

        Hotel hotelFromDatabase = hotelRepository.getHotel(hotelName);
        assertThat(hotelFromDatabase).isNotNull();
        assertThat(hotelFromDatabase.getName()).isEqualTo(hotelName);
        assertThat(hotelFromDatabase.getCategory()).isEqualTo(category);
    }
}
