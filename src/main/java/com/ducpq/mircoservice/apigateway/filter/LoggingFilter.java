package com.ducpq.mircoservice.apigateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * LoggingFilter
 *
 * @author Phan Qui Duc
 * @version 1.0
 * @since 2025-03-20
 */
@Component
public class LoggingFilter implements GlobalFilter {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		logger.info("Path of the request received -> {}", exchange.getRequest().getPath().value());
		return chain.filter(exchange);
	}
}
