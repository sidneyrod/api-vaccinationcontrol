package com.apirestvaccinationcontrol.tests.integration;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.apirestvaccinationcontrol.dto.VaccineRegistrationDTO;
import com.apirestvaccinationcontrol.services.VaccineRegistrationService;
import com.apirestvaccinationcontrol.services.exceptions.ResourceNotFoundException;


@SpringBootTest
@Transactional
public class VaccineRegistrationServiceIT {

	@Autowired
	private VaccineRegistrationService service;
	
	private long existingId;
	private long nonExistingId;
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
	} 
	
	@Test
	public void deleteShouldDoNothingWhenIdExists() {
		Assertions.assertDoesNotThrow(() -> {
			service.delete(existingId);
		});
	}
	
	@Test
	public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.delete(nonExistingId);
		});
	}
	
	@Test
	public void findByIdShouldReturnVaccineRegistrationDTOWhenIdExists() {
		VaccineRegistrationDTO result = service.findById(existingId);
		Assertions.assertNotNull(result);
	}
	
	@Test
	public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.findById(nonExistingId);
		});
	}
	
	@Test
	public void findAllShouldReturnVaccineRegistrationDTOWhenIdExists() {
		List<VaccineRegistrationDTO> result = service.findAll();
		Assertions.assertNotNull(result);
	}
	
	@Test
	public void updateShouldReturnVaccineRegistrationDTOWhenIdExists() {
		VaccineRegistrationDTO dto = new VaccineRegistrationDTO();
		VaccineRegistrationDTO result = service.update(existingId, dto);
		Assertions.assertNotNull(result);
	}
	
	@Test
	public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		VaccineRegistrationDTO dto = new VaccineRegistrationDTO(); 
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.update(nonExistingId, dto);
		});
	}
}
