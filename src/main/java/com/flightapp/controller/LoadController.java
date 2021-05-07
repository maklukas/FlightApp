package com.flightapp.controller;

import com.flightapp.domain.FlightLoad;
import com.flightapp.domain.dto.LoadDto;
import com.flightapp.service.LoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/loads")
public class LoadController {

    private final LoadService loadService;

    @Autowired
    public LoadController(LoadService loadService) {
        this.loadService = loadService;
    }

    @GetMapping(path = "/all")
    public List<FlightLoad> getAll() {
        return loadService.getLoads();
    }

    @GetMapping(params = {"flightNumber", "date"})
    public LoadDto getCargoWeight(@RequestParam int flightNumber, String date) {
        return loadService.getLoadInfo(flightNumber, LocalDate.parse(date));
    }

}
