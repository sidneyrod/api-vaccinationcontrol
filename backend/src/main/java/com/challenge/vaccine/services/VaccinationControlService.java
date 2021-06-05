package com.challenge.vaccine.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.vaccine.dto.VaccinationControlDTO;
import com.challenge.vaccine.entities.VaccinationControl;
import com.challenge.vaccine.repositories.VaccinationControlRepository;
import com.challenge.vaccine.services.exceptions.DatabaseException;
import com.challenge.vaccine.services.exceptions.ResourceNotFoundException;

@Service
public class VaccinationControlService {

	@Autowired
	private VaccinationControlRepository repository;
	
	@Transactional(readOnly = true)
	public Page<VaccinationControlDTO> findAllPaged(PageRequest pageRequest) {
		Page<VaccinationControl> list =  repository.findAll(pageRequest);
		return list.map(x -> new VaccinationControlDTO(x));	 
	}
	
	
	@Transactional(readOnly = true)
	public VaccinationControlDTO findById(Long id) {
		Optional<VaccinationControl> obj = repository.findById(id);
		VaccinationControl entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new VaccinationControlDTO(entity);
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
		Optional<VaccinationControl> obj = repository.findById(id);
		VaccinationControl entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id not found" + id));
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new VaccinationControlDTO(entity);
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

	private void copyDtoToEntity(VaccinationControlDTO dto, VaccinationControl entity) {
		entity.setVaccineName(dto.getVaccineName());
		entity.setUserEmail(dto.getUserEmail());
		entity.setVaccineDate(dto.getVaccineDate());
	}
}