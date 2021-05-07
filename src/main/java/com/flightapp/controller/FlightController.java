package com.flightapp.controller;

import com.flightapp.domain.FlightData;
import com.flightapp.domain.dto.FlightDto;
import com.flightapp.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final AirportService airportService;

    @Autowired
    public FlightController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping(path = "/all")
    public List<FlightData> getAll() {
        return airportService.getFlights();
    }

    @GetMapping(params = {"IATACode", "date"})
    public FlightDto getFlightInfo(@RequestParam String IATACode, String date) {
        return airportService.getFlightInfo(IATACode, LocalDate.parse(date));
    }
}
