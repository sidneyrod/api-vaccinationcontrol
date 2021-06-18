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
import com.apirestvaccinationcontrol.entities.Recipient;
import com.apirestvaccinationcontrol.repositories.RecipientRepository;
import com.apirestvaccinationcontrol.services.exceptions.DatabaseException;
import com.apirestvaccinationcontrol.services.exceptions.ResourceNotFoundException;

@Service
public class RecipientService {

	@Autowired
	private RecipientRepository repository;
	
	@Transactional(readOnly = true)
	public List<RecipientDTO> findAll() {
		List<Recipient> list = repository.findAll();
		return list.stream().map(x -> new RecipientDTO(x)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public RecipientDTO findById(Long id) {
		Optional<Recipient> obj = repository.findById(id);
		Recipient entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity " + id + " not found"));
		return new RecipientDTO(entity);
	}

	@Transactional
	public RecipientDTO insert(RecipientDTO dto) {
		Recipient entity = new Recipient();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new RecipientDTO(entity);
	}

	@Transactional
	public RecipientDTO update(Long id, RecipientDTO dto) {
		try {
			Recipient entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new RecipientDTO(entity);
		} catch (EntityNotFoundException e) {
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
	
	private void copyDtoToEntity(RecipientDTO dto, Recipient entity) {
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		entity.setPhoneNumber(dto.getPhoneNumber());
		entity.setNumberCpf(dto.getNumberCpf());
		entity.setBirthDate(dto.getBirthDate());
	}
}
