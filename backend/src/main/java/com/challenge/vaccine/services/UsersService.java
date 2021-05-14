package com.challenge.vaccine.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.vaccine.dto.UsersDTO;
import com.challenge.vaccine.entities.Users;
import com.challenge.vaccine.entities.Vaccine;
import com.challenge.vaccine.repositories.UsersRespository;
import com.challenge.vaccine.repositories.VaccineRepository;
import com.challenge.vaccine.services.exceptions.DatabaseException;
import com.challenge.vaccine.services.exceptions.ResourceNotFoundException;

@Service
public class UsersService {

	@Autowired
	private UsersRespository repository;
	
	@Autowired
	private VaccineRepository vaccineRepository;
	
	@Transactional(readOnly = true)
	public Page<UsersDTO> findAllPaged(Long vaccineId, String name, PageRequest pageRequest) {
		List<Vaccine> vaccines = (vaccineId == 0) ? null : Arrays.asList(vaccineRepository.getOne(vaccineId));
		Page<Users> page =  repository.find(vaccines, name, pageRequest);
		repository.findUsersWithVaccines(page.getContent());
		return page.map(x -> new UsersDTO(x, x.getVaccines()));
	}

	@Transactional(readOnly = true)
	public UsersDTO findById(Long id) {
		Optional<Users> obj = repository.findById(id);
		Users entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new UsersDTO(entity, entity.getVaccines());
	}

	@Transactional
	public UsersDTO insert(UsersDTO dto) {
		Users entity = new Users();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new UsersDTO(entity);
	}

	@Transactional
	public UsersDTO update(Long id, UsersDTO dto) {
		Optional<Users> obj = repository.findById(id);
		Users entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id not found" + id));
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new UsersDTO(entity);
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

	private void copyDtoToEntity(UsersDTO dto, Users entity) {
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		entity.setNumberCpf(dto.getNumberCpf());
		entity.setBirthDate(dto.getBirthDate());
	}
}