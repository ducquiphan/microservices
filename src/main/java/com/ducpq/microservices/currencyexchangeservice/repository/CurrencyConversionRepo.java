package com.ducpq.microservices.currencyexchangeservice.repository;

import com.ducpq.microservices.currencyexchangeservice.entity.CurrencyConversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CurrencyConversionRepo
 *
 * @author Admin
 * @version 1.0
 * @since 2025-03-14
 */
@Repository
public interface CurrencyConversionRepo extends JpaRepository<CurrencyConversion, Long> {
}
