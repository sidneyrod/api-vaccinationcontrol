package com.apirestvaccinationcontrol.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apirestvaccinationcontrol.dto.VaccineRegistrationDTO;
import com.apirestvaccinationcontrol.entities.VaccineRegistration;
import com.apirestvaccinationcontrol.repositories.VaccineRegistrationRepository;
import com.apirestvaccinationcontrol.services.exceptions.DatabaseException;
import com.apirestvaccinationcontrol.services.exceptions.ResourceNotFoundException;

@Service
public class VaccineRegistrationService {

	@Autowired
	private VaccineRegistrationRepository repository;

	@Transactional(readOnly = true)
	public List<VaccineRegistrationDTO> findAll() {
		List<VaccineRegistration> list = repository.findAll();
		return list.stream().map(x -> new VaccineRegistrationDTO(x)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public VaccineRegistrationDTO findById(Long id) {
		Optional<VaccineRegistration> obj = repository.findById(id);
		VaccineRegistration entity = obj
				.orElseThrow(() -> new ResourceNotFoundException("Entity " + id + " not found"));
		return new VaccineRegistrationDTO(entity);
	}

	@Transactional
	public VaccineRegistrationDTO insert(VaccineRegistrationDTO dto) {
		VaccineRegistration entity = new VaccineRegistration();
		entity.setNameVaccine(dto.getNameVaccine());
		entity = repository.save(entity);
		return new VaccineRegistrationDTO(entity);
	}

	@Transactional
	public VaccineRegistrationDTO update(Long id, VaccineRegistrationDTO dto) {
		try {
			VaccineRegistration entity = repository.getOne(id);
			entity.setNameVaccine(dto.getNameVaccine());
			entity = repository.save(entity);
			return new VaccineRegistrationDTO(entity);
		} 
		 catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Entity " + id + " not found");
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
