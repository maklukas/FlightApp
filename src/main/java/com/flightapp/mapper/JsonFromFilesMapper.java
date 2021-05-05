package com.flightapp.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flightapp.config.ConfigData;
import com.flightapp.domain.FlightData;
import com.flightapp.domain.FlightLoad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class JsonFromFilesMapper {

    private ObjectMapper mapper;

    @Autowired
    public JsonFromFilesMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public List<FlightLoad> getFlightLoads() {
        try {
            FlightLoad[] obj = mapper.readValue(ResourceUtils.getFile(ConfigData.CARGO_JSON_FILE_LOCATION), FlightLoad[].class);
            return new ArrayList<>(Arrays.asList(obj));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<FlightData> getFlightData() {
        try {
            FlightData[] obj = mapper.readValue(ResourceUtils.getFile(ConfigData.FLIGHT_JSON_FILE_LOCATION), FlightData[].class);
            return new ArrayList<>(Arrays.asList(obj));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
