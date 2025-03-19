package com.ducpq.mircoservice.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ApiGatewayConfiguration
 *
 * @author Phan Qui Duc
 * @version 1.0
 * @since 2025-03-20
 */
@Configuration
public class ApiGatewayConfiguration {
	
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		// url can be url to your microservice
		return builder.routes()
				.route(p -> p.path("/get")
						.filters(f -> f
								.addRequestHeader("MyHeader", "MyURI") // can be your authentication header
								.addRequestParameter("MyBody", "MyBody"))
						.uri("http://httpbin.org:80"))
				.route(p -> p.path("/currency-exchange/**")
						.uri("lb://currency-exchange-service")) //lb is load balance and currency-exchange-service is name of the service
				.route(p -> p.path("/currency-conversion/**")
						.uri("lb://currency-conversion-service"))
				.route(p -> p.path("/currency-conversion-new/**")
						.filters(f -> f.rewritePath("/currency-conversion-new/(?<segment>.*)", "/currency-conversion/feign/${segment}"))// use
						// (?<segment>.*) and ${segment} to define segment after the path you want to rewrite
						.uri("lb://currency-conversion-service"))
				.build();
	}
}
