package com.apirestvaccinationcontrol.tests.factory;

import com.apirestvaccinationcontrol.dto.RecipientDTO;
import com.apirestvaccinationcontrol.entities.Recipient;

public class RecipientFactory {
	
	public static Recipient createRecipient() {
		return new Recipient(1L, "Alex Ford", "alexford@gmail.com", "5511999876640", "12345678918", null);
	}
	
	public static RecipientDTO createRecipientDTO() {
		return new RecipientDTO(createRecipient());
	}
}
