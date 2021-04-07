package com.challenge.vaccine.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.vaccine.dto.VaccineDTO;
import com.challenge.vaccine.entities.Vaccine;
import com.challenge.vaccine.repositories.VaccineRepository;
import com.challenge.vaccine.services.exceptions.DatabaseException;
import com.challenge.vaccine.services.exceptions.ResourceNotFoundException;

@Service
public class VaccineService {

	@Autowired
	private VaccineRepository repository;
	
	@Transactional(readOnly = true)
	public Page<VaccineDTO> findAllPaged(PageRequest pageRequest) {
		Page<Vaccine> list =  repository.findAll(pageRequest);
		return list.map(x -> new VaccineDTO(x));	 
	}

	@Transactional(readOnly = true)
	public VaccineDTO findById(Long id) {
		Optional<Vaccine> obj = repository.findById(id);
		Vaccine entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new VaccineDTO(entity);
	}

	@Transactional
	public VaccineDTO insert(VaccineDTO dto) {
		Vaccine entity = new Vaccine();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new VaccineDTO(entity);
	}

	@Transactional
	public VaccineDTO update(Long id, VaccineDTO dto) {
		try {
			Vaccine entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new VaccineDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found" + id);
	}
}
	public void delete(Long id) {
		try {
		repository.deleteById(id);
		
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found" + id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}

	private void copyDtoToEntity(VaccineDTO dto, Vaccine entity) {
		entity.setVaccineName(dto.getVaccineName());
		entity.setUserEmail(dto.getUserEmail());
		entity.setVaccineDate(dto.getVaccineDate());
	}
	
}
