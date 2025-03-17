package com.ducpq.microservices.currencyconversionservice.controller;

import com.ducpq.microservices.currencyconversionservice.entity.CurrencyConversion;
import com.ducpq.microservices.currencyconversionservice.service.CurrencyConversionService;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

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
		HashMap<String, String> uriVariables = new HashMap<>();
		uriVariables.put("fromCurrency", fromCurrency);
		uriVariables.put("toCurrency", toCurrency);
		ResponseEntity<CurrencyConversion> response = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{fromCurrency" +
						"}/to" +
						"/{toCurrency}",
				CurrencyConversion.class, uriVariables);
		CurrencyConversion currencyConversion = response.getBody();
		currencyConversion.setQuantity(quantity);
		currencyConversion.setTotalCalculatedAmount(quantity.multiply(currencyConversion.getConversionMultiple()));
		return currencyConversion;
	}
}
