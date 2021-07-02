package com.apirestvaccinationcontrol.tests.factory;

import java.time.Instant;

import com.apirestvaccinationcontrol.dto.VaccinationControlDTO;
import com.apirestvaccinationcontrol.entities.Recipient;
import com.apirestvaccinationcontrol.entities.VaccinationControl;
import com.apirestvaccinationcontrol.entities.VaccineRegistration;

public class VaccinationControlFactory {
	
	public static VaccinationControl createVaccinationControl() {
		VaccinationControl control = new VaccinationControl(1L, "Brazil", 1, Instant.now());
		control.getRecipients().add(new Recipient(1L, "Alex Ford", "alexford@gmail.com", "5511999876640", "12345678918", null));
		control.getVaccines().add(new VaccineRegistration(4L, "Any Vaccine"));
		return control;
	}
	
	public static VaccinationControlDTO createVaccinationControlDTO() {
		VaccinationControl control = createVaccinationControl();
		return new VaccinationControlDTO(control, control.getRecipients(), control.getVaccines());
	}
	
	public static VaccinationControlDTO createVaccinationControlDTO(Long id) {
		VaccinationControlDTO dto = createVaccinationControlDTO();
		dto.setId(id);
		return dto;
	}
}
