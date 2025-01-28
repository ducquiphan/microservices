package com.ducpq.rest.microservices.constant;

import lombok.Getter;

/**
 * ApiPath
 *
 * @author Admin
 * @version 1.0
 * @since 2025-01-28
 */
@Getter
public enum ApiPath {
	API("/api"),
	V1_0("/1.0"),
	V1_1("/1.1"),
	PERSON("/person");
	
	public static final String API_PATH = "/api";
	public static final String V1_0_PATH = "/v1_0";
	public static final String V1_1_PATH = "/v1_1";
	public static final String PERSON_PATH = "/person";
	
	private final String path;
	
	ApiPath(String path) {
		this.path = path;
	}
	
	
}
