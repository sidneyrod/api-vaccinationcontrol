package com.apirestvaccinationcontrol.tests.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.apirestvaccinationcontrol.entities.VaccineRegistration;
import com.apirestvaccinationcontrol.repositories.VaccineRegistrationRepository;
import com.apirestvaccinationcontrol.tests.factory.VaccineRegistrationFactory;

@DataJpaTest
public class VaccineRegistrationRepositoryTests {
	
	@Autowired
	private VaccineRegistrationRepository repository;
	
	private long existingId;
	private long nonExistingId;
	private long countTotalVaccines;
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		countTotalVaccines = 3L;
	}
	
	@Test
	public void saveShouldPersistWithAutoincrementWhenIdIsNull() {
		VaccineRegistration vaccine = VaccineRegistrationFactory.createVaccineRegistration();
		vaccine.setId(null);
		vaccine = repository.save(vaccine);
		Optional<VaccineRegistration> result = repository.findById(vaccine.getId());
		Assertions.assertNotNull(vaccine.getId());
		Assertions.assertEquals(countTotalVaccines + 1L, vaccine.getId());
		Assertions.assertTrue(result.isPresent());
		Assertions.assertSame(result.get(), vaccine);
	}

	@Test
	public void deleteShouldDeleteObjectWhenIdExists() {
		repository.deleteById(existingId);
		Optional<VaccineRegistration> result = repository.findById(existingId);
		Assertions.assertFalse(result.isPresent());
	}
	
	@Test
	public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExist() {
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			repository.deleteById(nonExistingId);
		});
	}
}
