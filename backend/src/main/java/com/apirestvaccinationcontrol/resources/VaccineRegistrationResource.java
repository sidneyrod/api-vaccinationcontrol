package com.apirestvaccinationcontrol.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirestvaccinationcontrol.dto.VaccineRegistrationDTO;
import com.apirestvaccinationcontrol.services.VaccineRegistrationService;

@RestController
@RequestMapping(value = "/vaccines")
public class VaccineRegistrationResource {
	
	@Autowired
	private VaccineRegistrationService service;
	
	@GetMapping
	public ResponseEntity<List<VaccineRegistrationDTO>> findAll() {
		List<VaccineRegistrationDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<VaccineRegistrationDTO> findById(@PathVariable Long id) {
		VaccineRegistrationDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
}
