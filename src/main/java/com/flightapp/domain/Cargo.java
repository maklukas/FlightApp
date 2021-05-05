package com.flightapp.domain;

public class Cargo extends Load {

    @Override
    public String toString() {
        return "Cargo{" +
                "id=" + super.getId() +
                ", weight=" + super.getWeight() + super.getWeightUnit() + '\'' +
                ", " + super.getPieces() + "pcs" +
                '}';
    }

}
