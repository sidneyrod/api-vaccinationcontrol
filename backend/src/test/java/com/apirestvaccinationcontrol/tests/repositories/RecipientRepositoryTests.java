package com.apirestvaccinationcontrol.tests.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.apirestvaccinationcontrol.entities.Recipient;
import com.apirestvaccinationcontrol.repositories.RecipientRepository;
import com.apirestvaccinationcontrol.tests.factory.RecipientFactory;

@DataJpaTest
public class RecipientRepositoryTests {
	
	@Autowired
	private RecipientRepository repository;
	
	private long existingId;
	private long nonExistingId;
	private long countTotalRecipients;
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		countTotalRecipients = 7L;
	}
	
	@Test
	public void saveShouldPersistWithAutoincrementWhenIdIsNull() {
		Recipient recipient = RecipientFactory.createRecipient();
		recipient.setId(null);
		recipient = repository.save(recipient);
		Optional<Recipient> result = repository.findById(recipient.getId());
		Assertions.assertNotNull(recipient.getId());
		Assertions.assertEquals(countTotalRecipients + 1L, recipient.getId());
		Assertions.assertTrue(result.isPresent());
		Assertions.assertSame(result.get(), recipient);
	}

	@Test
	public void deleteShouldDeleteObjectWhenIdExists() {
		repository.deleteById(existingId);
		Optional<Recipient> result = repository.findById(existingId);
		Assertions.assertFalse(result.isPresent());
	}
	
	@Test
	public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExist() {
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			repository.deleteById(nonExistingId);
		});
	}
}
