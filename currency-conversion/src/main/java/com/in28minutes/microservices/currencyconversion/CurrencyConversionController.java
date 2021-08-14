package com.in28minutes.microservices.currencyconversion;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class CurrencyConversionController {

    final CurrencyExchangeProxy currencyExchangeProxy;

    public CurrencyConversionController(CurrencyExchangeProxy currencyExchangeProxy) {
        this.currencyExchangeProxy = currencyExchangeProxy;
    }

    @RequestMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion convertRate(@PathVariable("from") String from,
                                          @PathVariable("to") String to,
                                          @PathVariable("quantity") BigDecimal quantity) {
        HashMap<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity(
                "http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, uriVariables
        );

        CurrencyConversion exchangeRespone = responseEntity.getBody();

        return exchangeRespone
                .toBuilder()
                .quantity(quantity)
                .convertedValue(quantity.multiply(exchangeRespone.getConversionRate()))
                .build();
    }


    @RequestMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion convertRateUsingFeign(@PathVariable("from") String from,
                                                    @PathVariable("to") String to,
                                                    @PathVariable("quantity") BigDecimal quantity) {
        CurrencyConversion exchangeRate = currencyExchangeProxy.getExchangeRate(from, to);

        return exchangeRate
                .toBuilder()
                .quantity(quantity)
                .convertedValue(quantity.multiply(exchangeRate.getConversionRate()))
                .build();
    }
}
