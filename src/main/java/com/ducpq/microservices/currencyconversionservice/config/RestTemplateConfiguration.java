package com.ducpq.microservices.currencyconversionservice.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplateConfiguration
 *
 * @author Phan Qui Duc
 * @version 1.0
 * @since 2025-04-02
 */
@Configuration(proxyBeanMethods = false)
public class RestTemplateConfiguration {
	@Bean
	RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
