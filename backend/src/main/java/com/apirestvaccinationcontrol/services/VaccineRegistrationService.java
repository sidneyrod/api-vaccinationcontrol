package com.apirestvaccinationcontrol.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apirestvaccinationcontrol.dto.VaccineRegistrationDTO;
import com.apirestvaccinationcontrol.entities.VaccineRegistration;
import com.apirestvaccinationcontrol.repositories.VaccineRegistrationRepository;
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
		VaccineRegistration entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity " + id + " not found"));
		return new VaccineRegistrationDTO(entity);
	}
}
