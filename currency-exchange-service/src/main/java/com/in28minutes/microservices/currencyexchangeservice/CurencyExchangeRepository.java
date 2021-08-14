package com.in28minutes.microservices.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurencyExchangeRepository extends JpaRepository<CurrencyExchange, Integer>{
    Optional<CurrencyExchange> findByFromAndTo(String fromCurrency, String toCurrency);
}
