package com.flightapp.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class ConfigData {
    public static BigDecimal CONVERTER_LB_TO_KG_VALUE = BigDecimal.valueOf(0.45359237);
    public static String CARGO_JSON_FILE_LOCATION = "classpath:json/cargo.json";
    public static String FLIGHT_JSON_FILE_LOCATION = "classpath:json/flight.json";

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
