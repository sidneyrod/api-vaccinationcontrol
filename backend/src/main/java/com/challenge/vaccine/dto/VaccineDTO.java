package com.challenge.vaccine.dto;

import java.io.Serializable;
import java.time.Instant;

import com.challenge.vaccine.entities.Vaccine;

public class VaccineDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String vaccineName;
	private String userEmail;
	private Instant vaccineDate;
	
	public VaccineDTO() {
	}

	public VaccineDTO(Long id, String vaccineName, String userEmail, Instant vaccineDate) {
		this.id = id;
		this.vaccineName = vaccineName;
		this.userEmail = userEmail;
		this.vaccineDate = vaccineDate;
	}
	
	public VaccineDTO(Vaccine entity) {
		id = entity.getId();
		vaccineName = entity.getVaccineName();
		userEmail = entity.getUserEmail();
		vaccineDate = entity.getVaccineDate();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVaccineName() {
		return vaccineName;
	}

	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Instant getVaccineDate() {
		return vaccineDate;
	}

	public void setVaccineDate(Instant vaccineDate) {
		this.vaccineDate = vaccineDate;
	}
}