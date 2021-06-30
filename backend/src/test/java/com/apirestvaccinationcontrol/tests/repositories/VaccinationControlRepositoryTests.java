package com.apirestvaccinationcontrol.tests.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.apirestvaccinationcontrol.entities.VaccinationControl;
import com.apirestvaccinationcontrol.repositories.VaccinationControlRepository;
import com.apirestvaccinationcontrol.tests.factory.VaccinationControlFactory;

@DataJpaTest
public class VaccinationControlRepositoryTests {
	
	@Autowired
	private VaccinationControlRepository repository;
	
	private long existingId;
	private long nonExistingId;
	private long countTotalVaccinationControl;
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		countTotalVaccinationControl = 8L;
	}
	
	@Test
	public void saveShouldPersistWithAutoincrementWhenIdIsNull() {
		VaccinationControl control = VaccinationControlFactory.createVaccinationControl();
		control.setId(null);
		control = repository.save(control);
		Optional<VaccinationControl> result = repository.findById(control.getId());
		Assertions.assertNotNull(control.getId());
		Assertions.assertEquals(countTotalVaccinationControl + 1L, control.getId());
		Assertions.assertTrue(result.isPresent());
		Assertions.assertSame(result.get(), control);
	}

	@Test
	public void deleteShouldDeleteObjectWhenIdExists() {
		repository.deleteById(existingId);
		Optional<VaccinationControl> result = repository.findById(existingId);
		Assertions.assertFalse(result.isPresent());
	}
	
	@Test
	public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExist() {
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			repository.deleteById(nonExistingId);
		});
	}
}
