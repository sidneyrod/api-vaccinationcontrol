package com.apirestvaccinationcontrol.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
		VaccineRegistrationDTO list = service.findById(id);
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<VaccineRegistrationDTO> insert(@Valid @RequestBody VaccineRegistrationDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<VaccineRegistrationDTO> update(@PathVariable Long id, @Valid @RequestBody VaccineRegistrationDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<VaccineRegistrationDTO> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
