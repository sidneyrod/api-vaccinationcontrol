package com.challenge.vaccine.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
