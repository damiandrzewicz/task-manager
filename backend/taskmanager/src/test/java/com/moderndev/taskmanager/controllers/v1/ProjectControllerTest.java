package com.moderndev.taskmanager.controllers.v1;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.moderndev.taskmanager.api.v1.model.ProjectDTO;

import com.moderndev.taskmanager.services.ProjectService;
import com.moderndev.taskmanager.services.exceptions.ResourceNotFoundException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockitoSession;

//@ActiveProfiles("test")
//@WebMvcTest(controllers = ProjectController.class)
//@ContextConfiguration(classes = TestConfig.class)
class ProjectControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@MockBean
	ProjectService projectService;
	
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	
	// Verify HTTP request matching and input serialization
//	@Test
//	void post_whenValidUrlAndMethodAndContentType_thenReturns201() throws Exception {
//		ProjectDTO project = ProjectDTO.builder()
//				.id(123L)
//				.name("testname")
//				.description("testdescription")
//				.parentId(2L)
//				.created(LocalDateTime.parse("12-12-2000 12:12:12", formatter))
//				.build();
//		
//		mockMvc.perform(post(ProjectController.BASE_URL)
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(objectMapper.writeValueAsString(project)))
//			.andExpect(status().isCreated());
//	}
//	
//	// Verify input validation
//	@Test
//	void post_whenNameNull_thenReturns400() throws Exception {
//		ProjectDTO project = ProjectDTO.builder().build();
//		
//		var res = mockMvc.perform(post(ProjectController.BASE_URL)
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(objectMapper.writeValueAsString(project)))
//			.andExpect(status().isBadRequest())
//			.andExpect(jsonPath("$.message", equalTo("Validation error")))
//			.andReturn();
//		
//		System.out.println(res.getRequest().getContentAsString());
//	}
//	
//	@Test
//	void post_whenValidInput_thenReturnsProjectResource201() throws Exception {
//		
//		ProjectDTO project = ProjectDTO.builder()
//				.id(123L)
//				.name("testname")
//				.description("testdescription")
//				.parentId(2L)
//				.created(LocalDateTime.parse("12-12-2000 12:12:12", formatter))
//				.build();
//		
//	
//		var createdDate = LocalDateTime.now();
//		ProjectDTO returnDTO = ProjectDTO.builder()
//				.id(1L)
//				.name("testname")
//				.description("testdescription")
//				.parentId(2L)
//				.created(createdDate)
//				.build();
//		
//		Mockito.when(projectService.save(any())).thenReturn(returnDTO);
//		
//		mockMvc.perform(post(ProjectController.BASE_URL)
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(objectMapper.writeValueAsString(project)))
//			.andExpect(status().isCreated())
//			.andExpect(bodyMatchers.containsObjectAsJson(returnDTO));
//	}
//	
//	@Test
//	void get_findAll_whenValidInput_thenReturnsList200() throws Exception {
//		
//		List<ProjectDTO> result = new ArrayList<>();
//		result.add(new ProjectDTO());
//		result.add(new ProjectDTO());
//		
//		Mockito.when(projectService.findAll()).thenReturn(result);
//		
//		mockMvc.perform(get(ProjectController.BASE_URL)
//				.contentType(MediaType.APPLICATION_JSON))
//			.andExpect(status().isOk())
//			.andExpect(jsonPath("$.length()", equalTo(2)));
//	}
//	
//	@Test
//	void get_findAll_whenNoResults_thenReturnsList200() throws Exception {
//		
//		List<ProjectDTO> result = new ArrayList<>();
//		
//		Mockito.when(projectService.findAll()).thenReturn(result);
//		
//		mockMvc.perform(get(ProjectController.BASE_URL)
//				.contentType(MediaType.APPLICATION_JSON))
//			.andExpect(status().isOk())
//			.andExpect(jsonPath("$.length()", equalTo(0)));
//	}
//	
//	@Test 
//	void get_findById_whenValidInput_thenReturns200() throws Exception{
//
//		var project = ProjectDTO.builder().id(12L).build();
//		var result = Optional.of(project);
//		
//		
//		Mockito.when(projectService.findById(12L)).thenReturn(result);
//		
//		var res = mockMvc.perform(get(ProjectController.BASE_URL + "/{id}", 12L)
//				.contentType(MediaType.APPLICATION_JSON))
//			.andExpect(status().isOk())
//			.andExpect(bodyMatchers.containsObjectAsJson(project))
//			.andReturn();
//		
//		System.out.println(res.getResponse().getContentAsString());
//	}
//	
//	@Test 
//	void get_findById_whenNotExistingInput_thenReturns200() throws Exception{
//
//		Mockito.when(projectService.findById(12L)).thenReturn(Optional.empty());
//		
//		var res = mockMvc.perform(get(ProjectController.BASE_URL + "/{id}", 12L)
//				.contentType(MediaType.APPLICATION_JSON))
//			.andExpect(status().isNotFound())
//			.andExpect(jsonPath("$.message", equalTo("Resource not found")))
//			.andReturn();
//		
//		System.out.println(res.getResponse().getContentAsString());
//	}
//	
//	@Test
//	void put_whenEmptyId_thenReturns400() throws Exception {
//		ProjectDTO project = ProjectDTO.builder().build();
//		
//		mockMvc.perform(put(ProjectController.BASE_URL)
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(objectMapper.writeValueAsString(project)))
//			.andExpect(status().isBadRequest())
//			.andExpect(jsonPath("$.message", equalTo("Validation error")));
//			
//	}
//	
//	@Test
//	void put_whenNameEmpty_thenReturns404() throws Exception {
//		ProjectDTO project = ProjectDTO.builder().id(1L).name("").build();
//		
//		System.out.println(objectMapper.writeValueAsString(project));
//		
//		mockMvc.perform(put(ProjectController.BASE_URL)
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(objectMapper.writeValueAsString(project)))
//			.andExpect(status().isBadRequest())
//			.andExpect(jsonPath("$.message", equalTo("Validation error")));
//	}
//	
//	@Test
//	void put_whenNotFound_thenReturns404() throws Exception {
//		ProjectDTO project = ProjectDTO.builder().id(1L).name("test").build();
//		
//		Mockito.when(projectService.update(any())).thenThrow(ResourceNotFoundException.class);
//		
//		mockMvc.perform(put(ProjectController.BASE_URL)
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(objectMapper.writeValueAsString(project)))
//			.andExpect(status().isNotFound());
//	}
//	
//
//	
//	@Test
//	void put_whenFoundAndValidated_thenReturns200() throws Exception {
//		ProjectDTO project = ProjectDTO.builder().id(1L).name("test").build();
//		
//		Mockito.when(projectService.update(any())).thenReturn(project);
//		
//		var res = mockMvc.perform(put(ProjectController.BASE_URL)
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(objectMapper.writeValueAsString(project)))
//			.andExpect(status().isOk())
//			.andExpect(bodyMatchers.containsObjectAsJson(project))
//			.andReturn();
//		
//		System.out.println(res.getResponse().getContentAsString());
//	}
//	
//	@Test
//	void put_whenIgnoreCreated_thenReturns200() throws Exception {
//		var date1 = LocalDateTime.parse("12-12-2000 12:12:12", formatter);
//		var date2 = LocalDateTime.now();
//		
//		ProjectDTO project = ProjectDTO.builder().id(1L).name("test").created(date1).build();
//		var returnDTO = project.clone();
//		returnDTO.setCreated(date2);
//		
//		Mockito.when(projectService.update(any())).thenReturn(returnDTO);
//		
//		var res = mockMvc.perform(put(ProjectController.BASE_URL)
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(objectMapper.writeValueAsString(project)))
//			.andExpect(status().isOk())
//			.andExpect(bodyMatchers.containsObjectAsJson(returnDTO))
//			.andReturn();
//		
//		System.out.println(res.getResponse().getContentAsString());
//	}
	

}

