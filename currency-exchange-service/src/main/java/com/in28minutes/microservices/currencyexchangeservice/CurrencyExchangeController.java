package com.in28minutes.microservices.currencyexchangeservice;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
public class CurrencyExchangeController {

    private final Environment environment;
    private final CurencyExchangeRepository curencyExchangeRepository;

    public CurrencyExchangeController(Environment environment, CurencyExchangeRepository curencyExchangeRepository) {
        this.environment = environment;
        this.curencyExchangeRepository = curencyExchangeRepository;
    }

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange getExchangeRate(@PathVariable("from") String from,
                                            @PathVariable("to") String to) {

        String port = environment.getProperty("local.server.port");
        CurrencyExchange currencyExchange = curencyExchangeRepository.findByFromAndTo(from, to)
                .orElseThrow(() -> new NoSuchElementException("rate not found"));
        currencyExchange.setEnv(port+" currency exchange");
        return currencyExchange;
    }
}
