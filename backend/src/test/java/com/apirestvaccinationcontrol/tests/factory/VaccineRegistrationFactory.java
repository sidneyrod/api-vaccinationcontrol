package com.apirestvaccinationcontrol.tests.factory;

import com.apirestvaccinationcontrol.dto.VaccineRegistrationDTO;
import com.apirestvaccinationcontrol.entities.VaccineRegistration;

public class VaccineRegistrationFactory {
	
	public static VaccineRegistration createVaccineRegistration() {
		return new VaccineRegistration(4L, "Any Vaccine");
	}
	
	public static VaccineRegistrationDTO createVaccineRegistrationDTO() {
		return new VaccineRegistrationDTO(createVaccineRegistration());
	}
	
	public static VaccineRegistrationDTO createVaccineRegistrationDTO(Long id) {
		VaccineRegistrationDTO dto = createVaccineRegistrationDTO();
		dto.setId(id);
		return dto;
	}
}
