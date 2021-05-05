package com.flightapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Load {

    @JsonProperty("id")
    private int id;
    @JsonProperty("weight")
    private int weight;
    @JsonProperty("weightUnit")
    private String weightUnit;
    @JsonProperty("pieces")
    private int pieces;

    @Override
    public abstract String toString();
}
