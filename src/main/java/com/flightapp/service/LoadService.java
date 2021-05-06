package com.flightapp.service;

import com.flightapp.config.ConfigData;
import com.flightapp.domain.*;
import com.flightapp.mapper.JsonFromFilesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoadService {

    private final JsonFromFilesMapper mapper;

    @Autowired
    public LoadService(JsonFromFilesMapper mapper) {
        this.mapper = mapper;
    }

    public BigDecimal getCargoWeight(int flightId) {
        List<FlightLoad> flightLoads = mapper.getFlightLoads();
        List<Load> cargo = flightLoads.stream()
                .filter(flight -> flight.getFlightId() == flightId)
                .findAny()
                .get()
                .getCargo();

        return calculateWeight(cargo);
    }


    public BigDecimal getCargoWeight(FlightData flight) {
        List<FlightLoad> flightLoads = mapper.getFlightLoads();
        List<Load> cargo = flightLoads.stream()
                .filter(f -> f.getFlightId() == flight.getFlightId())
                .findAny()
                .get()
                .getCargo();

        return calculateWeight(cargo);
    }

    public BigDecimal getBaggageWeight(int flightId) {
        List<FlightLoad> flightLoads = mapper.getFlightLoads();
        List<Load> baggage = flightLoads.stream()
                .filter(flight -> flight.getFlightId() == flightId)
                .findAny()
                .get()
                .getBaggage();

        return calculateWeight(baggage);
    }

    public BigDecimal getBaggageWeight(FlightData flight) {
        List<FlightLoad> flightLoads = mapper.getFlightLoads();
        List<Load> baggage = flightLoads.stream()
                .filter(f -> f.getFlightId() == flight.getFlightId())
                .findAny()
                .get()
                .getBaggage();

        return calculateWeight(baggage);
    }

    public BigDecimal getTotalWeight(FlightData flight) {
        BigDecimal result = getBaggageWeight(flight);
        result = result.add(getCargoWeight(flight));
        return result;
    }

    public BigDecimal getTotalWeight(int flightId) {
        BigDecimal result = getBaggageWeight(flightId);
        result = result.add(getCargoWeight(flightId));
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

    private long calculateBaggageQty(FlightData flight) {
        List<FlightLoad> flightLoads = mapper.getFlightLoads();
        List<Load> baggage = flightLoads.stream()
                .filter(f -> f.getFlightId() == flight.getFlightId())
                .findAny()
                .get()
                .getBaggage();

        return calculateNumberOfLoads(baggage);
    }

    private long calculateBaggageQty(List<FlightData> flight) {
        List<FlightLoad> flightLoads = mapper.getFlightLoads();
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


}
