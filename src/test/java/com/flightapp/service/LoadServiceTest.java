package com.flightapp.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LoadServiceTest {

    @Autowired
    private LoadService service;

    @Test
    public void shouldGetCargoWeight() {
        //given
        //when
        BigDecimal result = service.getCargoWeight(7502, LocalDate.of(2015,2,1));

        //then
        Assertions.assertEquals(BigDecimal.valueOf(958.06136347), result);
    }

    @Test
    public void shouldGetNullCargoWeight() {
        //given
        //when
        BigDecimal result = service.getCargoWeight(0, null);

        //then
        Assertions.assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    public void shouldGetBaggageWeight() {
        //given
        //when
        BigDecimal result = service.getBaggageWeight(7502, LocalDate.of(2015,2,1));

        //then
        Assertions.assertEquals(BigDecimal.valueOf(2955.97719519), result);
    }

    @Test
    public void shouldGetNullBaggageWeight() {
        //given
        //when
        BigDecimal result = service.getBaggageWeight(0, LocalDate.of(3000,2,1));

        //then
        Assertions.assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    public void shouldGetTotalWeight() {
        //given
        //when
        BigDecimal result = service.getTotalWeight(7502, LocalDate.of(2015,2,1));

        //then
        Assertions.assertEquals(BigDecimal.valueOf(3914.03855866), result);
    }

    @Test
    public void shouldGetNullTotalWeight() {
        //given
        //when
        BigDecimal result = service.getTotalWeight(0, LocalDate.of(3000,2,1));

        //then
        Assertions.assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    public void shouldGetNumberOfBaggageArriving() {
        //given
        //when
        long result3317 = service.getNumberOfBaggageArriving("PPX", LocalDate.of(2015,2,1));
        long result4288 = service.getNumberOfBaggageArriving("MIT", LocalDate.of(2021,2,4));
        long result0 = service.getNumberOfBaggageArriving("KRK", LocalDate.of(2021,2,4));
        long resultNull = service.getNumberOfBaggageArriving("ABCDEF", null);
        //then
        assertEquals(3317, result3317);
        assertEquals(4288, result4288);
        assertEquals(0, result0);
        assertEquals(0, resultNull);
    }



    @Test
    public void shouldGetNumberOfBaggageDeparting() {
        //given
        //when
        long result3317 = service.getNumberOfBaggageDeparting("ANC", LocalDate.of(2015,2,1));
        long result4288 = service.getNumberOfBaggageDeparting("LAX", LocalDate.of(2021,2,4));
        long result0 = service.getNumberOfBaggageDeparting("YYZ", LocalDate.of(2021,2,4));
        long resultNull = service.getNumberOfBaggageArriving("ABCDEF", null);

        //then
        assertEquals(3317, result3317);
        assertEquals(4288, result4288);
        assertEquals(0, result0);
        assertEquals(0, resultNull);
    }

}