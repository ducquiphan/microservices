package com.ducpq.microservices.limitsservice.controller;

import com.ducpq.microservices.limitsservice.config.Configuration;
import com.ducpq.microservices.limitsservice.entity.Salary;
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
@RequestMapping("/salaries")
@AllArgsConstructor
public class SalaryController {
	
	private final Configuration configuration;
	
	@GetMapping()
	public Salary retrieveDefaultSalary(){
		return new Salary(configuration.getDefaultSalary());
	}
}
