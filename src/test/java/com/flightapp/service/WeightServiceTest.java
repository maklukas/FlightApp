package com.flightapp.service;

import com.flightapp.domain.FlightData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class WeightServiceTest {

    @Autowired
    private WeightService weightService;

    @Test
    public void testCargoWeight() {
        //given
        //when
        BigDecimal result = weightService.getCargoWeight(0);

        //then
        Assertions.assertEquals(BigDecimal.valueOf(958.06136347), result);
    }

    @Test
    public void testBaggageWeight() {
        //given
        //when
        BigDecimal result = weightService.getBaggageWeight(0);

        //then
        Assertions.assertEquals(BigDecimal.valueOf(2955.97719519), result);
    }


    @Test
    public void testBaggageWeightByFlight() {
        //given
        FlightData flightData = new FlightData();
        flightData.setFlightId(0);
        flightData.setFlightNumber(1863);
        flightData.setDepartureAirportIATACode("SEA");
        flightData.setArrivalAirportIATACode("GDN");
        flightData.setDepartureDate("2015-11-14T02:36:49 -01:00");

        //when
        BigDecimal result = weightService.getBaggageWeight(flightData);

        //then
        Assertions.assertEquals(BigDecimal.valueOf(2955.97719519), result);
    }

    @Test
    public void testCargoWeightByFlight() {
        //given
        FlightData flightData = new FlightData();
        flightData.setFlightId(0);
        flightData.setFlightNumber(1863);
        flightData.setDepartureAirportIATACode("SEA");
        flightData.setArrivalAirportIATACode("GDN");
        flightData.setDepartureDate("2015-11-14T02:36:49 -01:00");
        //when
        BigDecimal result = weightService.getCargoWeight(flightData);

        //then
        Assertions.assertEquals(BigDecimal.valueOf(958.06136347), result);
    }

    @Test
    public void testTotalWeightByFlight() {
        //given
        FlightData flightData = new FlightData();
        flightData.setFlightId(0);
        flightData.setFlightNumber(1863);
        flightData.setDepartureAirportIATACode("SEA");
        flightData.setArrivalAirportIATACode("GDN");
        flightData.setDepartureDate("2015-11-14T02:36:49 -01:00");
        //when
        BigDecimal result = weightService.getTotalWeight(flightData);

        //then
        Assertions.assertEquals(BigDecimal.valueOf(3914.03855866), result);
    }

    @Test
    public void testTotalWeight() {
        //given
        //when
        BigDecimal result = weightService.getTotalWeight(0);

        //then
        Assertions.assertEquals(BigDecimal.valueOf(3914.03855866), result);
    }
}