package com.ducpq.microservices.currencyexchangeservice.service;

import com.ducpq.microservices.currencyexchangeservice.entity.CurrencyConversion;

import java.util.List;

/**
 * CurrencyExchangeService
 *
 * @author Admin
 * @version 1.0
 * @since 2025-03-15
 */
public interface CurrencyConversionService {
	List<CurrencyConversion> findAllCurrencyExchanges();
	
	CurrencyConversion findCurrencyExchange(Long id);
	
	CurrencyConversion findCurrencyExchange(String fromCurrency, String toCurrency);
	
	CurrencyConversion createCurrencyExchange(CurrencyConversion currencyConversion);
	
	CurrencyConversion updateCurrencyExchange(CurrencyConversion currencyConversion);
	
	void deleteCurrencyExchange(Long id);
}
