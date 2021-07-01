package com.apirestvaccinationcontrol.tests.integration;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.apirestvaccinationcontrol.dto.VaccinationControlDTO;
import com.apirestvaccinationcontrol.services.VaccinationControlService;
import com.apirestvaccinationcontrol.services.exceptions.ResourceNotFoundException;


@SpringBootTest
@Transactional
public class VaccinationControlServiceIT {

	@Autowired
	private VaccinationControlService service;
	
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
	public void findByIdShouldReturnVaccinationControlDTOWhenIdExists() {
		VaccinationControlDTO result = service.findById(existingId);
		Assertions.assertNotNull(result);
	}
	
	@Test
	public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.findById(nonExistingId);
		});
	}
	
	@Test
	public void findAllShouldReturnVaccinationControlDTOWhenIdExists() {
		List<VaccinationControlDTO> result = service.findAll();
		Assertions.assertNotNull(result);
	}
	
	@Test
	public void updateShouldReturnVaccinationControlDTOWhenIdExists() {
		VaccinationControlDTO dto = new VaccinationControlDTO();
		VaccinationControlDTO result = service.update(existingId, dto);
		Assertions.assertNotNull(result);
	}
	
	@Test
	public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		VaccinationControlDTO dto = new VaccinationControlDTO(); 
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.update(nonExistingId, dto);
		});
	}
}
