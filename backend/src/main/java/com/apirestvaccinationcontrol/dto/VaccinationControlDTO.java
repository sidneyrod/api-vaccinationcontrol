package com.apirestvaccinationcontrol.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.apirestvaccinationcontrol.entities.Recipient;
import com.apirestvaccinationcontrol.entities.VaccinationControl;
import com.apirestvaccinationcontrol.entities.VaccineRegistration;

public class VaccinationControlDTO implements Serializable { 
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotNull(message = "The country must not be empty")
	@NotBlank(message = "Enter the country of vaccination")
	private String countryVaccination;
	
	@NotNull(message = "The dose amount must not be empty")
	private Integer numberDose;
	
	@NotNull(message = "The date of vaccine application must not be empty")
	private Instant vaccineApplicationDate;
	
	private List<RecipientDTO> recipients = new ArrayList<>();
	
	private List<VaccineRegistrationDTO> vaccines = new ArrayList<>();
	
	public VaccinationControlDTO() {
	}
	
	public VaccinationControlDTO(Long id, String countryVaccination, Integer numberDose, Instant vaccineApplicationDate) {
		this.id = id;
		this.countryVaccination = countryVaccination;
		this.numberDose = numberDose;
		this.vaccineApplicationDate = vaccineApplicationDate;
	}

	public VaccinationControlDTO(VaccinationControl entity) {
		id = entity.getId();
		countryVaccination = entity.getCountryVaccination();
		numberDose = entity.getNumberDose();
		vaccineApplicationDate = entity.getVaccineApplicationDate();
	}
	
	public VaccinationControlDTO(VaccinationControl entity, Set<Recipient> recipients, Set<VaccineRegistration> vaccines) {
		this(entity);
		recipients.forEach(rec -> this.recipients.add(new RecipientDTO(rec)));
		vaccines.forEach(vac -> this.vaccines.add(new VaccineRegistrationDTO(vac)));
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountryVaccination() {
		return countryVaccination;
	}

	public void setCountryVaccination(String countryVaccination) {
		this.countryVaccination = countryVaccination;
	}

	public Integer getNumberDose() {
		return numberDose;
	}

	public void setNumberDose(Integer numberDose) {
		this.numberDose = numberDose;
	}

	public Instant getVaccineApplicationDate() {
		return vaccineApplicationDate;
	}

	public void setVaccineApplicationDate(Instant vaccineApplicationDate) {
		this.vaccineApplicationDate = vaccineApplicationDate;
	}

	public List<VaccineRegistrationDTO> getVaccines() {
		return vaccines;
	}

	public List<RecipientDTO> getRecipients() {
		return recipients;
	}
}
