package com.challenge.vaccine.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.vaccine.entities.Users;
import com.challenge.vaccine.repositories.UsersRespository;

@RestController
@RequestMapping(value = "/users")
public class UsersController {

	@Autowired
	private UsersRespository usersRespository;
	
	@GetMapping
	@ResponseStatus(HttpStatus.CREATED)
	public List<Users> list() {
	return usersRespository.findAll();
	
	}
	
	@PostMapping
	public ResponseEntity<Users> add(@RequestBody Users users) {
		Users us = usersRespository.save(users);
		if (us != null) {
			return new ResponseEntity<Users>(new Users(), HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	
	}
	
}
