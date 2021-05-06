package com.flightapp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AirportServiceTest {

    @Autowired
    private AirportService service;

    @Test
    void shouldGetNumberOfFlightsDeparting() {
        //given
        //when
        long result1 = service.getNumberOfFlightsDeparting("LAX", LocalDate.of(2020,8,18));
        long result0 = service.getNumberOfFlightsDeparting("LAX", LocalDate.of(2015,2,1));
        //then
        assertEquals(1, result1);
        assertEquals(0, result0);
    }

    @Test
    void shouldGetNumberOfFlightsArriving() {
        //given
        //when
        long result1 = service.getNumberOfFlightsArriving("KRK", LocalDate.of(2021,3,18));
        long result0 = service.getNumberOfFlightsArriving("MIT", LocalDate.of(2015,2,1));
        //then
        assertEquals(1, result1);
        assertEquals(0, result0);
    }
}