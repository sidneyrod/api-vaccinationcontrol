package com.apirestvaccinationcontrol.dto;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.apirestvaccinationcontrol.entities.Recipient;

public class RecipientDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotNull(message = "The name must not be empty")
	@NotBlank(message = "Enter the name of the recipient")
	private String name;
	
	@Email(message = "Enter a valid email address")
	private String email;
	
	@NotNull(message = "The phone number must not be empty")
	private String phoneNumber;
	
	@NotNull(message = "The number CPF must not be empty")
	private String numberCpf;
	
	private Instant birthDate;

	public RecipientDTO() {
	}

	public RecipientDTO(Long id, String name, String email, String phoneNumber, String numberCpf, Instant birthDate) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.numberCpf = numberCpf;
		this.birthDate = birthDate;
	}
	
	public RecipientDTO(Recipient entity) {
		id = entity.getId();
		name = entity.getName();
		email = entity.getEmail();
		phoneNumber = entity.getPhoneNumber();
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getNumberCpf() {
		return numberCpf;
	}

	public void setNumberCpf(String numberCpf) {
		this.numberCpf = numberCpf;
	}

	public Instant getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Instant birthDate) {
		this.birthDate = birthDate;
	}
}
