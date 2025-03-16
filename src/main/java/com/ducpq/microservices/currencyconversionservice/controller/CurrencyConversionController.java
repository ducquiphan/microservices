package com.ducpq.microservices.currencyconversionservice.controller;

import com.ducpq.microservices.currencyconversionservice.entity.CurrencyConversion;
import com.ducpq.microservices.currencyconversionservice.service.CurrencyConversionService;
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
		CurrencyConversion currencyConversion = new CurrencyConversion(1L, fromCurrency, toCurrency, BigDecimal.ONE, quantity, BigDecimal.ONE, "");
		String port = environment.getProperty("local.server.port");
		currencyConversion.setEnvironment(port);
		return currencyConversion;
	}
}
