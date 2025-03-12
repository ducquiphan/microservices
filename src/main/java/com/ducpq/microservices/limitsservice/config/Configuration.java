package com.ducpq.microservices.limitsservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration
 *
 * @author Admin
 * @version 1.0
 * @since 2025-02-20
 */
@Component
@ConfigurationProperties("salary-service")
@Setter
@Getter
public class Configuration {
	private long defaultSalary;
}
