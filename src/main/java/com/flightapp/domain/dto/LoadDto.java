package com.flightapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoadDto {
    private int flightNumber;
    private ZonedDateTime date;
    private BigDecimal cargoWeight;
    private BigDecimal baggageWeight;
    private BigDecimal totalWeight;
}
