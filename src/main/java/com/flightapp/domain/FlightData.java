package com.flightapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

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
    private String departureDate;

}
