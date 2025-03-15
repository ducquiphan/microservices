package com.ducpq.microservices.currencyexchangeservice.service.impl;

import com.ducpq.microservices.currencyexchangeservice.entity.CurrencyExchange;
import com.ducpq.microservices.currencyexchangeservice.repository.CurrencyExchangeRepo;
import com.ducpq.microservices.currencyexchangeservice.service.CurrencyExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CurrencyExchangeService
 *
 * @author Admin
 * @version 1.0
 * @since 2025-03-15
 */

@Service
@RequiredArgsConstructor
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {
	private final CurrencyExchangeRepo currencyExchangeRepo;
	
	/**
	 * @return
	 */
	@Override
	public List<CurrencyExchange> findAllCurrencyExchanges() {
		return currencyExchangeRepo.findAll();
	}
	
	/**
	 * @param id
	 * @return
	 */
	@Override
	public CurrencyExchange findCurrencyExchange(Long id) {
		return null;
	}
	
	/**
	 * @param fromCurrency
	 * @param toCurrency
	 * @return
	 */
	@Override
	public CurrencyExchange findCurrencyExchange(String fromCurrency, String toCurrency) {
		return currencyExchangeRepo.findByFromAndTo(fromCurrency, toCurrency).orElse(null);
	}
	
	/**
	 * @param currencyExchange
	 * @return
	 */
	@Override
	public CurrencyExchange createCurrencyExchange(CurrencyExchange currencyExchange) {
		return null;
	}
	
	/**
	 * @param currencyExchange
	 * @return
	 */
	@Override
	public CurrencyExchange updateCurrencyExchange(CurrencyExchange currencyExchange) {
		return null;
	}
	
	/**
	 * @param id
	 */
	@Override
	public void deleteCurrencyExchange(Long id) {
	
	}
}
