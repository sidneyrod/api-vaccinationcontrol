package com.apirestvaccinationcontrol.tests.factory;

import com.apirestvaccinationcontrol.dto.VaccineRegistrationDTO;
import com.apirestvaccinationcontrol.entities.VaccineRegistration;

public class VaccineRegistrationFactory {
	
	public static VaccineRegistration createVaccineRegistration() {
		return new VaccineRegistration(1L, "Any Vaccine");
	}
	
	public static VaccineRegistrationDTO createVaccineRegistrationDTO() {
		return new VaccineRegistrationDTO(createVaccineRegistration());
	}
}
