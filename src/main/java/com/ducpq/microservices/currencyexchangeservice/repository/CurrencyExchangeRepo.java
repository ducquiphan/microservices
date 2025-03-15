package com.ducpq.microservices.currencyexchangeservice.repository;

import com.ducpq.microservices.currencyexchangeservice.entity.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * CurrencyExchangeRepo
 *
 * @author Admin
 * @version 1.0
 * @since 2025-03-14
 */
@Repository
public interface CurrencyExchangeRepo extends JpaRepository<CurrencyExchange,Long> {
	Optional<CurrencyExchange> findByFromAndTo(String fromCurrency, String toCurrency);
}
