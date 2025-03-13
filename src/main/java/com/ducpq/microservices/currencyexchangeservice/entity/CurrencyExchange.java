package com.ducpq.microservices.currencyexchangeservice.entity;


import lombok.*;

import java.math.BigDecimal;

/**
 * CurrencyExchange
 *
 * @author Admin
 * @version 1.0
 * @since 2025-03-13
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CurrencyExchange {
	private Long id;
	private String from;
	private String to;
	private BigDecimal conversionMultiple;
	private String environment;
	
	public CurrencyExchange(Long id, String from, String to, BigDecimal conversionMultiple) {
		this.id = id;
		this.from = from;
		this.to = to;
		this.conversionMultiple = conversionMultiple;
	}
}
