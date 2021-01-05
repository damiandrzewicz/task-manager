package com.moderndev.taskmanager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moderndev.taskmanager.controllers.ResponseBodyMatchers;

@TestConfiguration
public class TestConfig {

	@Autowired
	ObjectMapper objectMapper;
	
	@Bean
	public ResponseBodyMatchers responseBodyMatchers() {
		return new ResponseBodyMatchers(objectMapper);
	}

}
