package com.apirestvaccinationcontrol.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apirestvaccinationcontrol.entities.VaccineRegistration;
import com.apirestvaccinationcontrol.repositories.VaccineRegistrationRepository;
import com.apirestvaccinationcontrol.services.exceptions.DatabaseException;
import com.apirestvaccinationcontrol.services.exceptions.ResourceNotFoundException;

@Service
public class VaccineRegistrationService {

	@Autowired
	private VaccineRegistrationRepository repository;

	@Transactional(readOnly = true)
	public List<VaccineRegistration> findAll() {
		List<VaccineRegistration> list = repository.findAll();
		return list;
	}

	@Transactional(readOnly = true)
	public VaccineRegistration findById(Long id) {
		Optional<VaccineRegistration> obj = repository.findById(id);
		VaccineRegistration entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity " + id + " not found"));
		return entity;
	}

	@Transactional
	public VaccineRegistration insert(VaccineRegistration entity) {
		entity = repository.save(entity);
		return entity;
	}

	@Transactional
	public VaccineRegistration update(VaccineRegistration entity) {
		try {
			repository.save(entity);
			return entity;
		} 
		 catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Entity not found");
		}
	}

	public void delete(Long id) {
		try {
		repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Entity " + id + " not found");
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}
}
