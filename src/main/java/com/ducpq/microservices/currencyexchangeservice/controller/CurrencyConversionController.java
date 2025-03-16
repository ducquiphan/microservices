package com.ducpq.microservices.currencyexchangeservice.controller;

import com.ducpq.microservices.currencyexchangeservice.entity.CurrencyConversion;
import com.ducpq.microservices.currencyexchangeservice.service.CurrencyConversionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * CurrencyConversionController
 *
 * @author Admin
 * @version 1.0
 * @since 2025-03-13
 */
@RestController
@RequestMapping("/currency-conversion")
@AllArgsConstructor
public class CurrencyConversionController {
	
	private Environment environment;
	private CurrencyConversionService currencyConversionService;
	
	@GetMapping("/from/{fromCurrency}/to/{toCurrency}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversion(@PathVariable("fromCurrency") String fromCurrency,
														  @PathVariable("toCurrency") String toCurrency,
														  @PathVariable("quantity") BigDecimal quantity) {
		//		CurrencyExchange currencyExchange = new CurrencyExchange(1000L, fromCurrency, toCurrency,
		//				BigDecimal.valueOf(50));
		CurrencyConversion currencyConversion = currencyConversionService.findCurrencyExchange(fromCurrency, toCurrency);
		if (currencyConversion == null) {
			throw new EntityNotFoundException("Not found currency exchange from " + fromCurrency + " to " + toCurrency + " at the moment!");
		}
		String port = environment.getProperty("local.server.port");
		currencyConversion.setEnvironment(port);
		return currencyConversion;
	}
}
