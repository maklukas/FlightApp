package com.flightapp.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.math.BigDecimal;

@Configuration
@EnableSwagger2
public class ConfigData {
    public static BigDecimal CONVERTER_LB_TO_KG_VALUE = BigDecimal.valueOf(0.45359237);
    public static String CARGO_JSON_FILE_LOCATION = "classpath:json/cargo.json";
    public static String FLIGHT_JSON_FILE_LOCATION = "classpath:json/flight.json";

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public Docket get() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.flightapp.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
