package com.flightapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDto {
    private String IATACode;
    private ZonedDateTime date;
    private long departingFlightsQuantity;
    private long arrivingFlightsQuantity;
    private long arrivingBaggageQuantity;
    private long departingBaggageQuantity;

}
