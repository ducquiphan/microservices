package com.ducpq.microservices.currencyexchangeservice.controller;

import com.ducpq.microservices.currencyexchangeservice.entity.CurrencyExchange;
import com.ducpq.microservices.currencyexchangeservice.service.CurrencyExchangeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CurrencyExchangeController
 *
 * @author Admin
 * @version 1.0
 * @since 2025-03-13
 */
@RestController
@RequestMapping("/currency-exchange")
@AllArgsConstructor
public class CurrencyExchangeController {
	
	private Environment environment;
	private CurrencyExchangeService currencyExchangeService;
	
	@GetMapping("/from/{fromCurrency}/to/{toCurrency}")
	public CurrencyExchange retrieveExchangeValue(@PathVariable("fromCurrency") String fromCurrency,
												  @PathVariable("toCurrency") String toCurrency) {
		//		CurrencyExchange currencyExchange = new CurrencyExchange(1000L, fromCurrency, toCurrency,
		//				BigDecimal.valueOf(50));
		CurrencyExchange currencyExchange = currencyExchangeService.findCurrencyExchange(fromCurrency, toCurrency);
		if (currencyExchange == null) {
			throw new EntityNotFoundException("Not found currency exchange from " + fromCurrency + " to " + toCurrency + " at the moment!");
		}
		String port = environment.getProperty("local.server.port");
		currencyExchange.setEnvironment(port);
		return currencyExchange;
	}
}
