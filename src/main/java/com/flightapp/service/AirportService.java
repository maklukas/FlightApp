package com.flightapp.service;

import com.flightapp.domain.FlightData;
import com.flightapp.mapper.JsonFromFilesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class AirportService {
    private final JsonFromFilesMapper mapper;

    @Autowired
    public AirportService(JsonFromFilesMapper mapper) {
        this.mapper = mapper;
    }

    public long getNumberOfFlightsDeparting(String IATACode, LocalDate date) {
        List<FlightData> flightData = mapper.getFlightData();
        return flightData.stream()
                .filter(flight -> flight.getDepartureAirportIATACode().equals(IATACode) &&
                        ZonedDateTime.of(date, flight.getDepartureDate().toLocalTime(), flight.getDepartureDate().getZone()).isEqual(flight.getDepartureDate()))
                .count();
    }

    public long getNumberOfFlightsArriving(String IATACode, LocalDate date) {
        //assumed that arriving date is the same as departing
        List<FlightData> flightData = mapper.getFlightData();
        return flightData.stream()
                .filter(flight -> flight.getArrivalAirportIATACode().equals(IATACode) &&
                        ZonedDateTime.of(date, flight.getDepartureDate().toLocalTime(), flight.getDepartureDate().getZone()).isEqual(flight.getDepartureDate()))
                .count();
    }

}
