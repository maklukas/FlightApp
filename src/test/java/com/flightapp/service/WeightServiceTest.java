package com.flightapp.service;

import com.flightapp.domain.FlightData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class WeightServiceTest {

    @Autowired
    private LoadService service;

    @Test
    public void shouldGetCargoWeight() {
        //given
        //when
        BigDecimal result = service.getCargoWeight(0);

        //then
        Assertions.assertEquals(BigDecimal.valueOf(958.06136347), result);
    }

    @Test
    public void shouldGetBaggageWeight() {
        //given
        //when
        BigDecimal result = service.getBaggageWeight(0);

        //then
        Assertions.assertEquals(BigDecimal.valueOf(2955.97719519), result);
    }


    @Test
    public void shouldGetBaggageWeightByFlight() {
        //given
        FlightData flightData = new FlightData();
        flightData.setFlightId(0);
        flightData.setFlightNumber(1863);
        flightData.setDepartureAirportIATACode("SEA");
        flightData.setArrivalAirportIATACode("GDN");
        flightData.setDepartureDate("2015-11-14T02:36:49-01:00");

        //when
        BigDecimal result = service.getBaggageWeight(flightData);

        //then
        Assertions.assertEquals(BigDecimal.valueOf(2955.97719519), result);
    }

    @Test
    public void shouldGetCargoWeightByFlight() {
        //given
        FlightData flightData = new FlightData();
        flightData.setFlightId(0);
        flightData.setFlightNumber(1863);
        flightData.setDepartureAirportIATACode("SEA");
        flightData.setArrivalAirportIATACode("GDN");
        flightData.setDepartureDate("2015-11-14T02:36:49-01:00");
        //when
        BigDecimal result = service.getCargoWeight(flightData);

        //then
        Assertions.assertEquals(BigDecimal.valueOf(958.06136347), result);
    }

    @Test
    public void shouldGetTotalWeightByFlight() {
        //given
        FlightData flightData = new FlightData();
        flightData.setFlightId(0);
        flightData.setFlightNumber(1863);
        flightData.setDepartureAirportIATACode("SEA");
        flightData.setArrivalAirportIATACode("GDN");
        flightData.setDepartureDate("2015-11-14T02:36:49-01:00");
        //when
        BigDecimal result = service.getTotalWeight(flightData);

        //then
        Assertions.assertEquals(BigDecimal.valueOf(3914.03855866), result);
    }

    @Test
    public void shouldGetTotalWeight() {
        //given
        //when
        BigDecimal result = service.getTotalWeight(0);

        //then
        Assertions.assertEquals(BigDecimal.valueOf(3914.03855866), result);
    }

    @Test
    public void shouldGetNumberOfBaggageArriving() {
        //given
        //when
        long result3317 = service.getNumberOfBaggageArriving("PPX", LocalDate.of(2015,2,1));
        long result4288 = service.getNumberOfBaggageArriving("MIT", LocalDate.of(2021,2,4));
        long result0 = service.getNumberOfBaggageArriving("KRK", LocalDate.of(2021,2,4));
        //then
        assertEquals(3317, result3317);
        assertEquals(4288, result4288);
        assertEquals(0, result0);
    }

    @Test
    public void shouldGetNumberOfBaggageDeparting() {
        //given
        //when
        long result3317 = service.getNumberOfBaggageDeparting("ANC", LocalDate.of(2015,2,1));
        long result4288 = service.getNumberOfBaggageDeparting("LAX", LocalDate.of(2021,2,4));
        long result0 = service.getNumberOfBaggageDeparting("YYZ", LocalDate.of(2021,2,4));
        //then
        assertEquals(3317, result3317);
        assertEquals(4288, result4288);
        assertEquals(0, result0);
    }

}