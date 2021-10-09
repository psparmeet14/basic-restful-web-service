package com.pslearning.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {
	
	// Using URI Versioning
	@GetMapping("/person/v1")
	public PersonV1 personV1() {
		return new PersonV1("Parmeet Singh");
	}
	
	@GetMapping("/person/v2")
	public PersonV2 personV2() {
		return new PersonV2(new Name("Parmeet", "Singh"));
	}

	// Using Request Parameter Versioning
	@GetMapping(value = "/person/param", params = "version=1")
	public PersonV1 requestV1() {
		return new PersonV1("Parmeet Singh");
	}
	
	@GetMapping(value = "/person/param", params = "version=2")
	public PersonV2 requestV2() {
		return new PersonV2(new Name("Parmeet", "Singh"));
	}
	
	// Using Header Versioning
	@GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
	public PersonV1 headerV1() {
		return new PersonV1("Parmeet Singh");
	}
	
	@GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
	public PersonV2 headerV2() {
		return new PersonV2(new Name("Parmeet", "Singh"));
	}
	
	// Using Content Negotiation Versioning or Media Type Versioning or Accept Header Versioning or Produces Versioning
	@GetMapping(value = "/person/produces", produces = "application/my.company.app-v1+json")
	public PersonV1 producesV1() {
		return new PersonV1("Parmeet Singh");
	}
	
	@GetMapping(value = "/person/produces", produces = "application/my.company.app-v2+json")
	public PersonV2 producesV2() {
		return new PersonV2(new Name("Parmeet", "Singh"));
	}
}
