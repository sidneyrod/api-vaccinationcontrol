package com.apirestvaccinationcontrol.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.apirestvaccinationcontrol.entities.VaccineRegistration;

public class VaccineRegistrationDTO implements Serializable { 
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotNull(message = "The vaccine name must not be empty")
	@NotBlank(message = "Enter the name of the vaccine")
	private String nameVaccine;
	
	public VaccineRegistrationDTO() {
	}

	public VaccineRegistrationDTO(Long id, String nameVaccine) {
		this.id = id;
		this.nameVaccine = nameVaccine;
	}
	
	public VaccineRegistrationDTO(VaccineRegistration entity) {
		id = entity.getId();
		nameVaccine = entity.getNameVaccine();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameVaccine() {
		return nameVaccine;
	}

	public void setNameVaccine(String nameVaccine) {
		this.nameVaccine = nameVaccine;
	}
}
