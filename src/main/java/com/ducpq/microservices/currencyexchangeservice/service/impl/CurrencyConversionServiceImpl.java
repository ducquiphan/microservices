package com.ducpq.microservices.currencyexchangeservice.service.impl;

import com.ducpq.microservices.currencyexchangeservice.repository.CurrencyConversionRepo;
import com.ducpq.microservices.currencyexchangeservice.service.CurrencyConversionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
