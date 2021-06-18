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

import com.apirestvaccinationcontrol.dto.RecipientDTO;
import com.apirestvaccinationcontrol.dto.VaccinationControlDTO;
import com.apirestvaccinationcontrol.dto.VaccineRegistrationDTO;
import com.apirestvaccinationcontrol.entities.Recipient;
import com.apirestvaccinationcontrol.entities.VaccinationControl;
import com.apirestvaccinationcontrol.entities.VaccineRegistration;
import com.apirestvaccinationcontrol.repositories.RecipientRepository;
import com.apirestvaccinationcontrol.repositories.VaccinationControlRepository;
import com.apirestvaccinationcontrol.repositories.VaccineRegistrationRepository;
import com.apirestvaccinationcontrol.services.exceptions.DatabaseException;
import com.apirestvaccinationcontrol.services.exceptions.ResourceNotFoundException;

@Service
public class VaccinationControlService {

	@Autowired
	private VaccinationControlRepository repository;
	
	@Autowired
	private RecipientRepository recipientRepository;
	
	@Autowired
	private VaccineRegistrationRepository vaccineRegistrationRepository;

	@Transactional(readOnly = true)
	public List<VaccinationControlDTO> findAll() {
		List<VaccinationControl> list = repository.findAll();
		return list.stream().map(x -> new VaccinationControlDTO(x)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public VaccinationControlDTO findById(Long id) {
		Optional<VaccinationControl> obj = repository.findById(id);
		VaccinationControl entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity " + id + " not found"));
		return new VaccinationControlDTO(entity, entity.getRecipients(), entity.getVaccines());
	}

	@Transactional
	public VaccinationControlDTO insert(VaccinationControlDTO dto) {
		VaccinationControl entity = new VaccinationControl();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new VaccinationControlDTO(entity);
	}

	@Transactional
	public VaccinationControlDTO update(Long id, VaccinationControlDTO dto) {
		try {
			VaccinationControl entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new VaccinationControlDTO(entity);
		} 
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Entity " + id + " not found");
		}
	}

	@Transactional
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
	
	private void copyDtoToEntity(VaccinationControlDTO dto, VaccinationControl entity) {
		entity.setCountryVaccination(dto.getCountryVaccination());
		entity.setNumberDose(dto.getNumberDose());
		entity.setVaccineApplicationDate(dto.getVaccineApplicationDate());
		
		entity.getRecipients().clear();
		entity.getVaccines().clear();
		
		for (RecipientDTO recDto : dto.getRecipients()) {
			Recipient recipient = recipientRepository.getOne(recDto.getId());
			entity.getRecipients().add(recipient);
		}
		
		for (VaccineRegistrationDTO vrDto : dto.getVaccines()) {
			VaccineRegistration vaccineRegistration = vaccineRegistrationRepository.getOne(vrDto.getId());
			entity.getVaccines().add(vaccineRegistration);
		}
	}
}
