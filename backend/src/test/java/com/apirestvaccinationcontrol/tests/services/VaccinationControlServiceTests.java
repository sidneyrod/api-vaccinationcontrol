package com.apirestvaccinationcontrol.tests.services;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.apirestvaccinationcontrol.dto.VaccinationControlDTO;
import com.apirestvaccinationcontrol.entities.VaccinationControl;
import com.apirestvaccinationcontrol.repositories.VaccinationControlRepository;
import com.apirestvaccinationcontrol.services.VaccinationControlService;
import com.apirestvaccinationcontrol.services.exceptions.DatabaseException;
import com.apirestvaccinationcontrol.services.exceptions.ResourceNotFoundException;
import com.apirestvaccinationcontrol.tests.factory.VaccinationControlFactory;

@ExtendWith(SpringExtension.class)
public class VaccinationControlServiceTests {

	@InjectMocks
	private VaccinationControlService service;
	
	@Mock
	private VaccinationControlRepository repository;
	
	private long existingId;
	private long nonExistingId;
	private long dependentId;
	private VaccinationControl control;
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		dependentId = 2L;
		control = VaccinationControlFactory.createVaccinationControl();
		
		doNothing().when(repository).deleteById(existingId);
		doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(nonExistingId);
		doThrow(DataIntegrityViolationException.class).when(repository).deleteById(dependentId);
		
		when(repository.findById(existingId)).thenReturn(Optional.of(control));
		when(repository.findById(nonExistingId)).thenReturn(Optional.empty());
		
		when(repository.getOne(existingId)).thenReturn(control);
		doThrow(EntityNotFoundException.class).when(repository).getOne(nonExistingId);
				
		when(repository.save(ArgumentMatchers.any())).thenReturn(control);
	} 
	
	@Test
	public void deleteShouldDoNothingWhenIdExists() {
		Assertions.assertDoesNotThrow(() -> {
			service.delete(existingId);
		});
			verify(repository, times(1)).deleteById(existingId);
	}
	
	@Test
	public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.delete(nonExistingId);
		});
			verify(repository, times(1)).deleteById(nonExistingId);
	}
	
	@Test
	public void deleteShouldThrowDatabaseExceptionWhenIdDoesNotExist() {
		Assertions.assertThrows(DatabaseException.class, () -> {
			service.delete(dependentId);
		});
			verify(repository, times(1)).deleteById(dependentId);
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

