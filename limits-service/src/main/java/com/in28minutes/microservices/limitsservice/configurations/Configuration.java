package com.in28minutes.microservices.limitsservice.configurations;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(value = "limits-service")
public class Configuration {
    private int maximum;
    private int minimum;
}
