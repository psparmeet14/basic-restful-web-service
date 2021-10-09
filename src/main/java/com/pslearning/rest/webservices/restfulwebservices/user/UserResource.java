package com.pslearning.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	/*
	 * Notes:
	 * HATEOAS - Hypermedia as the engine of application state
	 */
	
	
	@Autowired
	private UserDaoService service;
	
	@GetMapping(path = "/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}
	
	@GetMapping(path = "/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		User user = service.findById(id);
		
		if (user == null)
			throw new UserNotFoundException("User with id-"+id+" not found in the system.");
		
		//HATEOAS
		//"all-users", SERVER_PATH + "/users"
		//retrieveAllUsers
		EntityModel<User> resource = EntityModel.of(user);
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));
		
		return resource;
	}
	
	@DeleteMapping(path = "/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = service.deleteById(id);
		if (user == null)
			throw new UserNotFoundException("Deletion failed! User with id-"+id+" is not found in the system.");
	}
	
	// input - details of user
	// output - CREATED and Return the created URI like  /users/{id}
	@PostMapping(path = "/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User createdUser = service.save(user);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(createdUser.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
}
