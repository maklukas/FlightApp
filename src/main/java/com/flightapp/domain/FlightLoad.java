package com.flightapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightLoad {

    @JsonProperty("flightId")
    private int flightId;
    @JsonProperty("baggage")
    private List<Load> baggage;
    @JsonProperty("cargo")
    private List<Load> cargo;

    public void setBaggage(List<Baggage> baggage) {
        this.baggage = new ArrayList<>(baggage);
    }

    public void setCargo(List<Cargo> cargo) {
        this.cargo = new ArrayList<>(cargo);
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }
}
