package com.apirestvaccinationcontrol.tests.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.apirestvaccinationcontrol.dto.VaccineRegistrationDTO;
import com.apirestvaccinationcontrol.services.VaccineRegistrationService;
import com.apirestvaccinationcontrol.services.exceptions.DatabaseException;
import com.apirestvaccinationcontrol.services.exceptions.ResourceNotFoundException;
import com.apirestvaccinationcontrol.tests.factory.VaccineRegistrationFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class VaccineRegistrationResourceTests {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private VaccineRegistrationService service;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private long existingId;
	private long nonExistingId;
	private long dependentId;
	private VaccineRegistrationDTO newVaccineRegistrationDTO;
	private VaccineRegistrationDTO existingVaccineDTO;
	private List<VaccineRegistrationDTO> list;
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		dependentId = 3L;
		
		newVaccineRegistrationDTO = VaccineRegistrationFactory.createVaccineRegistrationDTO(null);
		existingVaccineDTO = VaccineRegistrationFactory.createVaccineRegistrationDTO(existingId);
		
		list = new ArrayList<>();
		
		when(service.findById(existingId)).thenReturn(existingVaccineDTO);
		when(service.findById(nonExistingId)).thenThrow(ResourceNotFoundException.class);
		
		when(service.findAll()).thenReturn(list);
		
		when(service.insert(any())).thenReturn(existingVaccineDTO);
		
		when(service.update(eq(existingId), any())).thenReturn(existingVaccineDTO);
		when(service.update(eq(nonExistingId), any())).thenThrow(ResourceNotFoundException.class);
		
		doNothing().when(service).delete(existingId);
		doThrow(ResourceNotFoundException.class).when(service).delete(nonExistingId);
		doThrow(DatabaseException.class).when(service).delete(dependentId);
	}
	
	@Test
	public void findByIdShouldReturnVaccineRegistrationDTOWhenIdExists() throws Exception {
		ResultActions result = mockMvc.perform(get("/vaccines/{id}", existingId).accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id").exists());
		result.andExpect(jsonPath("$.id").value(existingId));
	}
	
	@Test
	public void findByIdShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
		ResultActions result = mockMvc.perform(get("/vaccines/{id}", nonExistingId).accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isNotFound());
	}
	
	@Test
	public void findAllShouldReturnList() throws Exception {
		ResultActions result = mockMvc.perform(get("/vaccines").accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk());
	}
	
	@Test
	public void insertShouldReturnCreatedVaccineRegistrationDTOWhenValidData() throws Exception {
		String jsonBody = objectMapper.writeValueAsString(newVaccineRegistrationDTO);
		
		ResultActions result = mockMvc.perform(post("/vaccines")
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isCreated());
		result.andExpect(jsonPath("$.id").exists());
	}
	
	@Test
	public void insertShouldReturnUnprocessableEntityWhenVaccineNameIsEmpty() throws Exception {
		newVaccineRegistrationDTO.setNameVaccine("");
		String jsonBody = objectMapper.writeValueAsString(newVaccineRegistrationDTO);
		
		ResultActions result = mockMvc.perform(post("/vaccines")
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isUnprocessableEntity());
	}
	
	@Test
	public void updateShouldReturnVaccineRegistrationDTOWhenIdExists() throws Exception {
		String jsonBody = objectMapper.writeValueAsString(newVaccineRegistrationDTO);
		String expectedNameVaccine = newVaccineRegistrationDTO.getNameVaccine();
		
		ResultActions result = mockMvc.perform(put("/vaccines/{id}", existingId)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id").exists());
		result.andExpect(jsonPath("$.id").value(existingId));
		result.andExpect(jsonPath("$.nameVaccine").value(expectedNameVaccine));
	}
	
	@Test
	public void updateShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
		String jsonBody = objectMapper.writeValueAsString(newVaccineRegistrationDTO);
		ResultActions result = mockMvc.perform(put("/vaccines/{id}", nonExistingId)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isNotFound());
	}
	
	@Test
	public void deleteShouldReturnNoContentWhenIdExists() throws Exception {
		ResultActions result = mockMvc.perform(delete("/vaccines/{id}", existingId)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNoContent());
	}
	
	@Test
	public void deleteShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
		ResultActions result = mockMvc.perform(delete("/vaccines/{id}", nonExistingId)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isNotFound());
	}
}
