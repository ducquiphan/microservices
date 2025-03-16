package com.ducpq.microservices.currencyexchangeservice.service.impl;

import com.ducpq.microservices.currencyexchangeservice.entity.CurrencyConversion;
import com.ducpq.microservices.currencyexchangeservice.repository.CurrencyConversionRepo;
import com.ducpq.microservices.currencyexchangeservice.service.CurrencyConversionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CurrencyConversionServiceImpl
 *
 * @author Admin
 * @version 1.0
 * @since 2025-03-15
 */

@Service
@RequiredArgsConstructor
public class CurrencyConversionServiceImpl implements CurrencyConversionService {
	private final CurrencyConversionRepo currencyConversionRepo;
	
	/**
	 * @return
	 */
	@Override
	public List<CurrencyConversion> findAllCurrencyExchanges() {
		return currencyConversionRepo.findAll();
	}
	
	/**
	 * @param id
	 * @return
	 */
	@Override
	public CurrencyConversion findCurrencyExchange(Long id) {
		return null;
	}
	
	/**
	 * @param fromCurrency
	 * @param toCurrency
	 * @return
	 */
	@Override
	public CurrencyConversion findCurrencyExchange(String fromCurrency, String toCurrency) {
		return currencyConversionRepo.findByFromAndTo(fromCurrency, toCurrency).orElse(null);
	}
	
	/**
	 * @param currencyConversion
	 * @return
	 */
	@Override
	public CurrencyConversion createCurrencyExchange(CurrencyConversion currencyConversion) {
		return null;
	}
	
	/**
	 * @param currencyConversion
	 * @return
	 */
	@Override
	public CurrencyConversion updateCurrencyExchange(CurrencyConversion currencyConversion) {
		return null;
	}
	
	/**
	 * @param id
	 */
	@Override
	public void deleteCurrencyExchange(Long id) {
	
	}
}
