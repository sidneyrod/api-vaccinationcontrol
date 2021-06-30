package com.apirestvaccinationcontrol.tests.factory;

import java.time.Instant;

import com.apirestvaccinationcontrol.dto.VaccinationControlDTO;
import com.apirestvaccinationcontrol.entities.VaccinationControl;

public class VaccinationControlFactory {
	
	public static VaccinationControl createVaccinationControl() {
		return new VaccinationControl(1L, "Brazil", 1, Instant.now());
	}
	
	public static VaccinationControlDTO createVaccinationControlDTO() {
		return new VaccinationControlDTO(createVaccinationControl());
	}
}
