package com.flightapp.domain;

public class Baggage extends Load {

    @Override
    public String toString() {
        return "Baggage{" +
                "id=" + super.getId() +
                ", weight=" + super.getWeight() + super.getWeightUnit() + '\'' +
                ", " + super.getPieces() + "pcs" +
                '}';
    }
}
