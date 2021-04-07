package com.challenge.vaccine.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.challenge.vaccine.entities.Users;
import com.challenge.vaccine.entities.Vaccine;

public class UsersDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String email;
	private String numberCpf;
	private Date birthDate;
	
	private List<VaccineDTO> vaccines = new ArrayList<>();
	
	public UsersDTO() {
	}

	public UsersDTO(Long id, String name, String email, String numberCpf, Date birthDate) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.numberCpf = numberCpf;
		this.birthDate = birthDate;
	}
	
	public UsersDTO(Users entity) {
		id = entity.getId();
		name = entity.getName();
		email = entity.getEmail();
		numberCpf = entity.getNumberCpf();
		birthDate = entity.getBirthDate();
	}
	
	public UsersDTO(Users entity, Set<Vaccine> vaccines) {
		this(entity);
		vaccines.forEach(vacc -> this.vaccines.add(new VaccineDTO(vacc)));
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumberCpf() {
		return numberCpf;
	}

	public void setNumberCpf(String numberCpf) {
		this.numberCpf = numberCpf;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public List<VaccineDTO> getVaccines() {
		return vaccines;
	}
}