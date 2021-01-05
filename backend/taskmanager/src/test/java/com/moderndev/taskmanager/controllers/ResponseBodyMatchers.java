package com.moderndev.taskmanager.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Component
public class ResponseBodyMatchers {
	  private ObjectMapper objectMapper;// = new ObjectMapper();
	  
	  public ResponseBodyMatchers(ObjectMapper objectMapper){
		  this.objectMapper = objectMapper;
		  //objectMapper.registerModule(new JavaTimeModule());
          //objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	  }

	  public <T> ResultMatcher containsObjectAsJson(
	      Object expectedObject) {
	    return mvcResult -> {
	      String jsonResponse = mvcResult.getResponse().getContentAsString();
	      String jsonExpected = objectMapper.writeValueAsString(expectedObject);
	      assertThat(jsonResponse).isEqualToIgnoringWhitespace(jsonExpected);
	    };
	  }
	}
