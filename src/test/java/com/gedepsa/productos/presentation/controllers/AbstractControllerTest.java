package com.gedepsa.productos.presentation.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

abstract class AbstractControllerTest {

	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	protected ObjectMapper objectMapper;
	
	protected void checkResponseBody(Object expected, MvcResult mvcResult) throws Exception {
		
		String responseBody = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
		String responseBodyEsperada = objectMapper.writeValueAsString(expected);
		
		//System.err.println(responseBody);
		//System.err.println(responseBodyEsperada);
		
		assertThat(responseBody).isEqualToIgnoringWhitespace(responseBodyEsperada);
		
	}
		
}
