package com.flightapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightData {

    @JsonProperty("flightId")
    private int flightId;
    @JsonProperty("flightNumber")
    private int flightNumber;
    @JsonProperty("departureAirportIATACode")
    private String departureAirportIATACode;
    @JsonProperty("arrivalAirportIATACode")
    private String arrivalAirportIATACode;
    @JsonProperty("departureDate")
    private ZonedDateTime departureDate;

    public void setDepartureDate(String departureDate) {
        this.departureDate = ZonedDateTime.parse(departureDate, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    public void setDepartureDate(ZonedDateTime departureDate) {
        this.departureDate = departureDate;
    }
}
