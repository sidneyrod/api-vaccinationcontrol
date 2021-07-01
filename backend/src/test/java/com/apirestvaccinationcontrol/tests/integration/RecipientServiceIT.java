package com.apirestvaccinationcontrol.tests.integration;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.apirestvaccinationcontrol.dto.RecipientDTO;
import com.apirestvaccinationcontrol.services.RecipientService;
import com.apirestvaccinationcontrol.services.exceptions.ResourceNotFoundException;


@SpringBootTest
@Transactional
public class RecipientServiceIT {

	@Autowired
	private RecipientService service;
	
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
	public void findByIdShouldReturnRecipientDTOWhenIdExists() {
		RecipientDTO result = service.findById(existingId);
		Assertions.assertNotNull(result);
	}
	
	@Test
	public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.findById(nonExistingId);
		});
	}
	
	@Test
	public void findAllShouldReturnRecipientDTOWhenIdExists() {
		List<RecipientDTO> result = service.findAll();
		Assertions.assertNotNull(result);
	}
	
	@Test
	public void updateShouldReturnRecipientDTOWhenIdExists() {
		RecipientDTO dto = new RecipientDTO();
		RecipientDTO result = service.update(existingId, dto);
		Assertions.assertNotNull(result);
	}
	
	@Test
	public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		RecipientDTO dto = new RecipientDTO(); 
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.update(nonExistingId, dto);
		});
	}
}
