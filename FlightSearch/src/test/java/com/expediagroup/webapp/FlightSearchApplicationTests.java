package com.expediagroup.webapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.expediagroup.webapp.model.Flights;
import com.expediagroup.webapp.service.FlightService;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class FlightSearchApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightService flightService;

    @Test
    public void findFlights() throws Exception {
        // given
    	LocalTime time = LocalTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
		time = LocalTime.parse("6:00 AM", formatter);
       
		Flights flight = new Flights();
        flight.setFlight("Air Canada");
        flight.setDeparture(time);

        ResponseEntity flightFound = new ResponseEntity<Object>(flight, HttpStatus.OK);
        given(flightService.findFlights("6:00 AM")).willReturn(flightFound);

        // when + then
        this.mockMvc.perform(get("/flight/6:00 AM"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'flight': 'Air Canada' ,'departure': '6:00 AM'}"));
    }
}
