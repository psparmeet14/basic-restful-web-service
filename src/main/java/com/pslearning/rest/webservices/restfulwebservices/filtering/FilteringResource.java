package com.pslearning.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringResource {

	@GetMapping("/static-filtering")
	public SomeBeanStaticFiltering retriveSomeBean() {
		return new SomeBeanStaticFiltering("value1", "value2", "value3");
	}
	
	@GetMapping("/static-filtering-list")
	public List<SomeBeanStaticFiltering> retrieveListOfSomeBeans() {
		return Arrays.asList(new SomeBeanStaticFiltering("value1", "value2", "value3"),
				new SomeBeanStaticFiltering("value1a", "value2a", "value3a"));
	}
	
	@GetMapping("/dynamic-filtering")
	public MappingJacksonValue retrieveSomeBeanDynamic() {
		
		SomeBeanDynamicFiltering someBean = new SomeBeanDynamicFiltering("value01", "value02", "value03");
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanDynamicFilter", filter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(filters);
		
		return mapping;
	}
	
	@GetMapping("/dynamic-filtering-list")
	public MappingJacksonValue retrieveListOfSomeBeanDynamic() {
		
		List<SomeBeanDynamicFiltering> someBeanList = Arrays.asList(new SomeBeanDynamicFiltering("value01", "value02", "value03"),
				new SomeBeanDynamicFiltering("value11", "value12", "value13"));
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanDynamicFilter", filter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(someBeanList);
		mapping.setFilters(filters);

		return mapping;
	}
	
}
