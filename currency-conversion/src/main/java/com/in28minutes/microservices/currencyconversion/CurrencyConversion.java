package com.in28minutes.microservices.currencyconversion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
public class CurrencyConversion {
    private Long id;
    private String from;
    private String to;
    private BigDecimal conversionRate;
    private BigDecimal quantity;
    private BigDecimal convertedValue;
    private String env;
}
