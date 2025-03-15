package com.ducpq.microservices.currencyexchangeservice.service;

import com.ducpq.microservices.currencyexchangeservice.entity.CurrencyExchange;
import com.ducpq.microservices.currencyexchangeservice.repository.CurrencyExchangeRepo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CurrencyExchangeService
 *
 * @author Admin
 * @version 1.0
 * @since 2025-03-15
 */
public interface CurrencyExchangeService {
	public List<CurrencyExchange> findAllCurrencyExchanges();
	public CurrencyExchange findCurrencyExchange(Long id);
	public CurrencyExchange findCurrencyExchange(String fromCurrency, String toCurrency);
	public CurrencyExchange createCurrencyExchange(CurrencyExchange currencyExchange);
	public CurrencyExchange updateCurrencyExchange(CurrencyExchange currencyExchange);
	public void deleteCurrencyExchange(Long id);
}
