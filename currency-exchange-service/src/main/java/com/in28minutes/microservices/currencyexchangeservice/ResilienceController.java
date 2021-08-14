package com.in28minutes.microservices.currencyexchangeservice;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class ResilienceController {
    private Logger logger = LoggerFactory.getLogger(ResilienceController.class);

    @GetMapping("/currency-exchange/sample")
//    @Retry(name = "sample-api", fallbackMethod = "fallBackResponse")
    @CircuitBreaker(name = "default", fallbackMethod = "fallBackResponse")
    public String getSampleData() {
        logger.info("Inside Sample method");
        return new RestTemplate().getForEntity("http://localhost:8811/sample", String.class).getBody();
    }

    public String fallBackResponse(Exception ex) {
        return "fallbackResponse";
    }
}
