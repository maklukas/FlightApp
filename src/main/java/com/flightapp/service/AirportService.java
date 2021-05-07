package com.flightapp.service;

import com.flightapp.domain.FlightData;
import com.flightapp.domain.dto.FlightDto;
import com.flightapp.mapper.JsonFromFilesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class AirportService {
    private final JsonFromFilesMapper mapper;
    private final LoadService loadService;

    @Autowired
    public AirportService(JsonFromFilesMapper mapper, @Lazy LoadService loadService) {
        this.mapper = mapper;
        this.loadService = loadService;
    }

    public List<FlightData> getFlights() {
        return mapper.getFlightData();
    }

    public long getNumberOfFlightsDeparting(String IATACode, LocalDate date) {
        return getFlights().stream()
                .filter(flight -> flight.getDepartureAirportIATACode().equals(IATACode) &&
                        ZonedDateTime.of(date, flight.getDepartureDate().toLocalTime(), flight.getDepartureDate().getZone()).isEqual(flight.getDepartureDate()))
                .count();
    }

    public long getNumberOfFlightsArriving(String IATACode, LocalDate date) {
        //assumed that arriving date is the same as departing
        return getFlights().stream()
                .filter(flight -> flight.getArrivalAirportIATACode().equals(IATACode) &&
                        ZonedDateTime.of(date, flight.getDepartureDate().toLocalTime(), flight.getDepartureDate().getZone()).isEqual(flight.getDepartureDate()))
                .count();
    }

    public FlightData getTheFlightData(String IATACode, LocalDate date) {
        return getFlights().stream()
                .filter(flight -> (flight.getArrivalAirportIATACode().equals(IATACode) || flight.getDepartureAirportIATACode().equals(IATACode)) &&
                        ZonedDateTime.of(date, flight.getDepartureDate().toLocalTime(), flight.getDepartureDate().getZone()).isEqual(flight.getDepartureDate()))
                .findAny().orElse(null);
    }

    public FlightData getTheFlightData(int flightNumber, LocalDate date) {
        return getFlights().stream()
                .filter(flight -> flight.getFlightNumber() == flightNumber &&
                        ZonedDateTime.of(date, flight.getDepartureDate().toLocalTime(), flight.getDepartureDate().getZone()).isEqual(flight.getDepartureDate()))
                .findAny().orElse(null);
    }

    public FlightDto getFlightInfo(String IATACode, LocalDate date) {
        FlightData flightData = getTheFlightData(IATACode, date);
        ZonedDateTime zonedDateTime;

        if (flightData != null) {
            zonedDateTime = flightData.getDepartureDate();
        } else {
            return new FlightDto();
        }

        return new FlightDto(
                IATACode,
                zonedDateTime,
                getNumberOfFlightsDeparting(IATACode, date),
                getNumberOfFlightsArriving(IATACode, date),
                loadService.getNumberOfBaggageArriving(IATACode, date),
                loadService.getNumberOfBaggageDeparting(IATACode, date));
    }

}
