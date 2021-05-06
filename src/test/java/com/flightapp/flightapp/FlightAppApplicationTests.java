package com.flightapp.flightapp;

import com.flightapp.domain.FlightData;
import com.flightapp.mapper.JsonFromFilesMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class FlightAppApplicationTests {

    private JsonFromFilesMapper mapper;


    @Autowired
    public FlightAppApplicationTests(JsonFromFilesMapper mapper) {
        this.mapper = mapper;
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void shouldParseJSONCargoToObject() {
        //given
        mapper.getFlightLoads().forEach(System.out::println);
        //when

        //then

    }

    @Test
    public void shouldParseJSONFlightToObject() {
        //given
//        List<FlightData> flights = new ArrayList<>(mapper.getFlightData());
//        List<ZonedDateTime> dates = flights.stream()
//                .map(FlightData::getDepartureDate)
//                .collect(Collectors.toList());
//        dates.forEach(System.out::println);

        ZonedDateTime firstTime = LocalDateTime.of(2021,5,5,5,45,30).atZone(ZoneId.of("Asia/Tokyo"));
        //when
        LocalDate toCompare = LocalDate.of(2021,5,4);
        ZonedDateTime second = ZonedDateTime.of(toCompare, firstTime.toLocalTime(), firstTime.getZone());
        //then
        Assertions.assertTrue(firstTime.isEqual(second));
        System.out.println(firstTime);
    }

}
