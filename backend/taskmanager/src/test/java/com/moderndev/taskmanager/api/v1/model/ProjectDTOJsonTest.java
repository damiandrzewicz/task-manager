package com.moderndev.taskmanager.api.v1.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import com.fasterxml.jackson.databind.ObjectMapper;

@JsonTest
class ProjectDTOJsonTest {

	JacksonTester<ProjectDTO> json;
	
	ObjectMapper objectMapper;
	
	@BeforeEach
	void setUp() throws Exception {
		objectMapper = new ObjectMapper();
	}
	
	@Test
	void mapDtoToEntity_putValidation() {
		var projetDTO = ProjectDTO.builder().id(1L).build();
	}

	@Test
	void test() throws IOException {
		JacksonTester.initFields(this, objectMapper);
		
		var projetDTO = ProjectDTO.builder().id(1L).build();
		
		JsonContent<ProjectDTO> result = json.write(projetDTO);
		assertThat(result).hasJsonPathNumberValue("$.id");
	}

}
