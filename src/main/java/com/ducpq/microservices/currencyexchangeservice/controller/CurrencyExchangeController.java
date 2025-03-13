package com.ducpq.microservices.currencyexchangeservice.controller;

import com.ducpq.microservices.currencyexchangeservice.entity.CurrencyExchange;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * CurrencyExchangeController
 *
 * @author Admin
 * @version 1.0
 * @since 2025-03-13
 */
@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {
	@GetMapping("/from/{fromCurrency}/to/{toCurrency}")
	public CurrencyExchange retrieveExchangeValue(@PathVariable("fromCurrency") String fromCurrency,
												  @PathVariable("toCurrency") String toCurrency){
		return new CurrencyExchange(1000L, fromCurrency, toCurrency,
				BigDecimal.valueOf(50));
	}
}
