package com.challenge.vaccine.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.vaccine.entities.Users;
import com.challenge.vaccine.repositories.UsersRespository;

@RestController
@RequestMapping(value = "/users")
public class UsersController {

	@Autowired
	private UsersRespository usersRespository;
	
	@GetMapping
	public List<Users> list() {
	return usersRespository.findAll();
		
	}
}
