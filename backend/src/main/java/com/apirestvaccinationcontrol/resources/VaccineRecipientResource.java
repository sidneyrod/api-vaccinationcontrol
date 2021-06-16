package com.apirestvaccinationcontrol.resources;

import java.net.URI;
import java.util.List;

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

import com.apirestvaccinationcontrol.entities.VaccineRecipient;
import com.apirestvaccinationcontrol.services.VaccineRecipientService;

@RestController
@RequestMapping(value = "/recipient")
public class VaccineRecipientResource {
	
	@Autowired
	private VaccineRecipientService service;
	
	@GetMapping
	public ResponseEntity<List<VaccineRecipient>> findAll() {
		List<VaccineRecipient> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<VaccineRecipient> findById(@PathVariable Long id) {
		VaccineRecipient entity = service.findById(id);
		return ResponseEntity.ok().body(entity);
	}
	
	@PostMapping
	public ResponseEntity<VaccineRecipient> insert(@RequestBody VaccineRecipient entity) {
		entity = service.insert(entity);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
		return ResponseEntity.created(uri).body(entity);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<VaccineRecipient> update(@RequestBody VaccineRecipient entity) {
		entity = service.update(entity);
		return ResponseEntity.ok().body(entity);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
