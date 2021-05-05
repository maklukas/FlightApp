package com.flightapp.flightapp;

import com.flightapp.domain.Baggage;
import com.flightapp.domain.Load;
import com.flightapp.mapper.JsonFromFilesMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        mapper.getFlightData().forEach(System.out::println);
        //when

        //then

    }

}
