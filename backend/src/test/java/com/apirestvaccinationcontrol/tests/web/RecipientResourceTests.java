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

import com.apirestvaccinationcontrol.dto.RecipientDTO;
import com.apirestvaccinationcontrol.services.RecipientService;
import com.apirestvaccinationcontrol.services.exceptions.DatabaseException;
import com.apirestvaccinationcontrol.services.exceptions.ResourceNotFoundException;
import com.apirestvaccinationcontrol.tests.factory.RecipientFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class RecipientResourceTests {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RecipientService service;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private long existingId;
	private long nonExistingId;
	private long dependentId;
	private RecipientDTO newRecipientDTO;
	private RecipientDTO existingRecipientDTO;
	private List<RecipientDTO> list;
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		dependentId = 3L;
		
		newRecipientDTO = RecipientFactory.createRecipientDTO(null);
		existingRecipientDTO = RecipientFactory.createRecipientDTO(existingId);
		
		list = new ArrayList<>();
		
		when(service.findById(existingId)).thenReturn(existingRecipientDTO);
		when(service.findById(nonExistingId)).thenThrow(ResourceNotFoundException.class);
		
		when(service.findAll()).thenReturn(list);
		
		when(service.insert(any())).thenReturn(existingRecipientDTO);
		
		when(service.update(eq(existingId), any())).thenReturn(existingRecipientDTO);
		when(service.update(eq(nonExistingId), any())).thenThrow(ResourceNotFoundException.class);
		
		doNothing().when(service).delete(existingId);
		doThrow(ResourceNotFoundException.class).when(service).delete(nonExistingId);
		doThrow(DatabaseException.class).when(service).delete(dependentId);
	}
	
	@Test
	public void findByIdShouldReturnRecipientDTOWhenIdExists() throws Exception {
		ResultActions result = mockMvc.perform(get("/recipients/{id}", existingId).accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id").exists());
		result.andExpect(jsonPath("$.id").value(existingId));
	}
	
	@Test
	public void findByIdShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
		ResultActions result = mockMvc.perform(get("/recipients/{id}", nonExistingId).accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isNotFound());
	}
	
	@Test
	public void findAllShouldReturnList() throws Exception {
		ResultActions result = mockMvc.perform(get("/recipients").accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk());
	}
	
	@Test
	public void insertShouldReturnCreatedRecipientDTOWhenValidData() throws Exception {
		String jsonBody = objectMapper.writeValueAsString(newRecipientDTO);
		
		ResultActions result = mockMvc.perform(post("/recipients")
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isCreated());
		result.andExpect(jsonPath("$.id").exists());
	}
	
	@Test
	public void insertShouldReturnUnprocessableEntityWhenVaccineNameIsEmpty() throws Exception {
		newRecipientDTO.setName("");
		newRecipientDTO.setEmail("");
		newRecipientDTO.setPhoneNumber("");
		newRecipientDTO.setNumberCpf("");
		newRecipientDTO.setBirthDate(null);
		String jsonBody = objectMapper.writeValueAsString(newRecipientDTO);
		
		ResultActions result = mockMvc.perform(post("/recipients")
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isUnprocessableEntity());
	}
	
	@Test
	public void updateShouldReturnRecipientDTOWhenIdExists() throws Exception {
		String jsonBody = objectMapper.writeValueAsString(newRecipientDTO);
				
		ResultActions result = mockMvc.perform(put("/recipients/{id}", existingId)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id").exists());
		result.andExpect(jsonPath("$.id").value(existingId));
	}
	
	@Test
	public void updateShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
		String jsonBody = objectMapper.writeValueAsString(newRecipientDTO);
		ResultActions result = mockMvc.perform(put("/recipients/{id}", nonExistingId)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isNotFound());
	}
	
	@Test
	public void deleteShouldReturnNoContentWhenIdExists() throws Exception {
		ResultActions result = mockMvc.perform(delete("/recipients/{id}", existingId)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNoContent());
	}
	
	@Test
	public void deleteShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
		ResultActions result = mockMvc.perform(delete("/recipients/{id}", nonExistingId)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isNotFound());
	}
}
