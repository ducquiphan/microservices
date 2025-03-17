package com.ducpq.microservices.currencyconversionservice.proxy;

import com.ducpq.microservices.currencyconversionservice.entity.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * CurrencyExchangeProxy
 *
 * @author Admin
 * @version 1.0
 * @since 2025-03-17
 */
@FeignClient(name = "currency-exchange",
		url = "localhost:8000/currency-exchange")
public interface CurrencyExchangeProxy {
	@GetMapping("/from/{fromCurrency}/to/{toCurrency}")
	CurrencyConversion retrieveExchangeValue(@PathVariable("fromCurrency") String fromCurrency,
											 @PathVariable("toCurrency") String toCurrency);
}
