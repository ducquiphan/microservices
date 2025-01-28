package com.ducpq.rest.microservices.controller;

import com.ducpq.rest.microservices.constant.ApiPath;
import com.ducpq.rest.microservices.entity.Name;
import com.ducpq.rest.microservices.entity.Person;
import com.ducpq.rest.microservices.entity.PersonV1;
import com.ducpq.rest.microservices.entity.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * VersioningPersionController
 *
 * @author Admin
 * @version 1.0
 * @since 2025-01-28
 */
@RestController
@RequestMapping(ApiPath.API_PATH)
public class VersioningPersonController {
	@GetMapping(ApiPath.V1_0_PATH + ApiPath.PERSON_PATH)
	public Person getFirstVersionOfPerson() {
		return new PersonV1("Phan Qui Duc");
	}
	
	@GetMapping(ApiPath.V1_1_PATH + ApiPath.PERSON_PATH)
	public Person getSecondVersionOfPerson() {
		return new PersonV2(new Name("Phan", "Qui Duc"));
	}
}
