package com.ducpq.rest.microservices.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * SomeBean
 *
 * @author Admin
 * @version 1.0
 * @since 2025-01-28
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
//@JsonIgnoreProperties("field1")
@JsonFilter("SomeBeanFilter")
public class SomeBean {
	private String field1;
	//@JsonIgnore
	private String field2;
	private String field3;
}
