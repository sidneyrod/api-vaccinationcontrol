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

import com.challenge.vaccine.dto.UsersDTO;
import com.challenge.vaccine.entities.Users;
import com.challenge.vaccine.repositories.UsersRespository;
import com.challenge.vaccine.services.exceptions.DatabaseException;
import com.challenge.vaccine.services.exceptions.ResourceNotFoundException;

@Service
public class UsersService {

	@Autowired
	private UsersRespository repository;
	
	@Transactional(readOnly = true)
	public Page<UsersDTO> findAllPaged(PageRequest pageRequest) {
		Page<Users> list =  repository.findAll(pageRequest);
		return list.map(x -> new UsersDTO(x));	 
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
		try {
			Users entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new UsersDTO(entity);
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

	private void copyDtoToEntity(UsersDTO dto, Users entity) {
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		entity.setNumberCpf(dto.getNumberCpf());
		entity.setBirthDate(dto.getBirthDate());
	}
}
