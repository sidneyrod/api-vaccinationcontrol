package com.apirestvaccinationcontrol.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_vaccinationcontrol")
public class VaccinationControl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String countryVaccination;
	private Integer numberDose;
	private Instant vaccineApplicationDate;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "recipient_id")
	private VaccineRecipient recipient;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "registration_id")
	private VaccineRegistration registration;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant created;

	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant updated;

	public VaccinationControl() {
	}
	
	public VaccinationControl(Long id, String countryVaccination, Integer numberDose, Instant vaccineApplicationDate,
			VaccineRecipient recipient, VaccineRegistration registration) {
		this.id = id;
		this.countryVaccination = countryVaccination;
		this.numberDose = numberDose;
		this.vaccineApplicationDate = vaccineApplicationDate;
		this.recipient = recipient;
		this.registration = registration;
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

	public VaccineRecipient getRecipient() {
		return recipient;
	}

	public void setRecipient(VaccineRecipient recipient) {
		this.recipient = recipient;
	}

	public VaccineRegistration getRegistration() {
		return registration;
	}

	public void setRegistration(VaccineRegistration registration) {
		this.registration = registration;
	}

	@PrePersist
	public void prePersist() {
		created = Instant.now();
	}

	@PreUpdate
	public void preUpdate() {
		updated = Instant.now();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VaccinationControl other = (VaccinationControl) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
