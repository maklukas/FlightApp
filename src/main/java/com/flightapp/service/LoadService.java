package com.flightapp.service;

import com.flightapp.config.ConfigData;
import com.flightapp.domain.*;
import com.flightapp.domain.dto.FlightDto;
import com.flightapp.domain.dto.LoadDto;
import com.flightapp.mapper.JsonFromFilesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoadService {

    private final JsonFromFilesMapper mapper;
    private final AirportService airportService;

    @Autowired
    public LoadService(JsonFromFilesMapper mapper, @Lazy AirportService airportService) {
        this.mapper = mapper;
        this.airportService = airportService;
    }

    public List<FlightLoad> getLoads() {
        return mapper.getFlightLoads();
    }

    public BigDecimal getCargoWeight(int flightNumber, LocalDate date) {
        FlightData flightData = airportService.getTheFlightData(flightNumber, date);
        FlightLoad theLoad = getTheFlightLoad(flightData);
        List<Load> cargo = new ArrayList<>();
        if (theLoad != null) {
            cargo = theLoad.getCargo();
        }
        return calculateWeight(cargo);
    }

    public BigDecimal getBaggageWeight(int flightNumber, LocalDate date) {
        FlightData flightData = airportService.getTheFlightData(flightNumber, date);
        FlightLoad theLoad = getTheFlightLoad(flightData);
        List<Load> baggage = new ArrayList<>();
        if (theLoad != null) {
            baggage = theLoad.getBaggage();
        }
        return calculateWeight(baggage);
    }

    private FlightLoad getTheFlightLoad(FlightData flightData) {
        if (flightData == null) {
            return null;
        }
        return getLoads().stream()
                .filter(flight -> flight.getFlightId() == flightData.getFlightId())
                .findAny()
                .orElse(null);
    }

    public BigDecimal getTotalWeight(int flightNumber, LocalDate date) {
        BigDecimal result = getBaggageWeight(flightNumber, date);
        result = result.add(getCargoWeight(flightNumber, date));
        return result;
    }

    private BigDecimal calculateWeight(List<Load> loads) {
        BigDecimal result = BigDecimal.ZERO;
        for(Load c: loads) {
            if (c.getWeightUnit().equals("lb")) {
                result = result.add(ConfigData.CONVERTER_LB_TO_KG_VALUE.multiply(BigDecimal.valueOf(c.getWeight())));
            } else {
                result = result.add(BigDecimal.valueOf(c.getWeight()));
            }
        }
        return result;
    }

    private long calculateBaggageQty(FlightData flightData) {
        FlightLoad flightLoad = getTheFlightLoad(flightData);
        List<Load> baggage = new ArrayList<>();
        if (flightLoad != null) {
            baggage = flightLoad.getBaggage();
        }
        return calculateNumberOfLoads(baggage);
    }

    private long calculateBaggageQty(List<FlightData> flight) {
        long result = 0;
        for(FlightData flightData: flight) {
            result += calculateBaggageQty(flightData);
        }
        return result;
    }

    private long calculateNumberOfLoads(List<Load> loads) {
        long result = 0;
        for(Load c: loads) {
            result += c.getPieces();
        }
        return result;
    }

    public long getNumberOfBaggageArriving(String IATACode, LocalDate date) {
        List<FlightData> flightData = mapper.getFlightData().stream()
                .filter(flight -> flight.getArrivalAirportIATACode().equals(IATACode) &&
                        ZonedDateTime.of(date, flight.getDepartureDate().toLocalTime(), flight.getDepartureDate().getZone()).isEqual(flight.getDepartureDate()))
                .collect(Collectors.toList());

        return calculateBaggageQty(flightData);
    }

    public long getNumberOfBaggageDeparting(String IATACode, LocalDate date) {
        List<FlightData> flightData = mapper.getFlightData().stream()
                .filter(flight -> flight.getDepartureAirportIATACode().equals(IATACode) &&
                        ZonedDateTime.of(date, flight.getDepartureDate().toLocalTime(), flight.getDepartureDate().getZone()).isEqual(flight.getDepartureDate()))
                .collect(Collectors.toList());

        return calculateBaggageQty(flightData);
    }



    public LoadDto getLoadInfo(int flightNumber, LocalDate date) {
        FlightData flightData = airportService.getTheFlightData(flightNumber, date);
        ZonedDateTime zonedDateTime;

        if (flightData != null) {
            zonedDateTime = flightData.getDepartureDate();
        } else {
            return new LoadDto();
        }
                return new LoadDto(
                flightNumber,
                zonedDateTime,
                getCargoWeight(flightNumber, date),
                getBaggageWeight(flightNumber, date),
                getTotalWeight(flightNumber, date)
        );
    }

}
