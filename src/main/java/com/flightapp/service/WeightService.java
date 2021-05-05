package com.flightapp.service;

import com.flightapp.config.ConfigData;
import com.flightapp.domain.*;
import com.flightapp.mapper.JsonFromFilesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class WeightService {

    private JsonFromFilesMapper mapper;

    @Autowired
    public WeightService(JsonFromFilesMapper mapper) {
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
        List<Load> baggages = flightLoads.stream()
                .filter(flight -> flight.getFlightId() == flightId)
                .findAny()
                .get()
                .getBaggage();

        return calculateWeight(baggages);
    }

    public BigDecimal getBaggageWeight(FlightData flight) {
        List<FlightLoad> flightLoads = mapper.getFlightLoads();
        List<Load> baggages = flightLoads.stream()
                .filter(f -> f.getFlightId() == flight.getFlightId())
                .findAny()
                .get()
                .getBaggage();

        return calculateWeight(baggages);
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
}
