package com.ducpq.rest.microservices.controller;

import com.ducpq.rest.microservices.entity.SomeBean;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * FilteringController
 *
 * @author Admin
 * @version 1.0
 * @since 2025-01-28
 */
@RestController
public class FilteringController {
	@GetMapping("/filtering")
	public MappingJacksonValue filtering() {
		SomeBean someBean = new SomeBean("value1", "value2", "value3");
		// Use this to create a filter
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
		
		// Clarify filter on fields
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");
		
		// Add the above filter into created filter of an entity
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		
		// Add filter into MappingJacksonValue
		mappingJacksonValue.setFilters(filters);
		return mappingJacksonValue;
	}
	
	@GetMapping("/filtering-list")
	public MappingJacksonValue filteringList() {
		
		List<SomeBean> someBeans = Arrays.asList(new SomeBean("value1", "value2", "value3"),
				new SomeBean("value4", "value5", "value6"));
		
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBeans);
		
		return getFilteredMappingJacksonValue(mappingJacksonValue, "SomeBeanFilter", "field2");
	}
	
	private MappingJacksonValue getFilteredMappingJacksonValue(MappingJacksonValue mappingJacksonValue, String filterId, String... includedFields) {
		// Clarify filter on fields
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(includedFields);
		
		FilterProvider filters = new SimpleFilterProvider().addFilter(filterId, filter);
		
		mappingJacksonValue.setFilters(filters);
		
		return mappingJacksonValue;
	}
}
