package com.challenge.vaccine.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.vaccine.entities.Vaccine;
import com.challenge.vaccine.repositories.VaccineRespository;

@RestController
@RequestMapping(value = "/vaccine")
public class VaccineController {

	@Autowired
	private VaccineRespository vaccineRespository;
	
	@GetMapping
	public List<Vaccine> list() {
	return vaccineRespository.findAll();
	
	}
	
	@PostMapping
	public ResponseEntity<Vaccine> add(@RequestBody Vaccine vaccine) {
		Vaccine vac = vaccineRespository.save(vaccine);
		if (vac != null) {
			return new ResponseEntity<Vaccine>(new Vaccine(), HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		} 
		
	}
	
}
