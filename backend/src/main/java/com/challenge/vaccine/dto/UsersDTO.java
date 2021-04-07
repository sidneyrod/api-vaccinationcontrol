package com.challenge.vaccine.dto;

import java.io.Serializable;
import java.util.Date;

import com.challenge.vaccine.entities.Users;

public class UsersDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String email;
	private String numberCpf;
	private Date birthDate;
	
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
}