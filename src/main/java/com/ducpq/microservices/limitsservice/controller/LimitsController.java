package com.ducpq.microservices.limitsservice.controller;

import com.ducpq.microservices.limitsservice.config.Configuration;
import com.ducpq.microservices.limitsservice.entity.Limits;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * LimitsController
 *
 * @author Admin
 * @version 1.0
 * @since 2025-02-20
 */
@RestController
@RequestMapping("/limits")
@AllArgsConstructor
public class LimitsController {
	
	private final Configuration configuration;
	
	@GetMapping()
	public Limits retrieveLimits(){
		return new Limits(configuration.getMinimum(), configuration.getMaximum());
//		return new Limits(1,1000);
	}
}
