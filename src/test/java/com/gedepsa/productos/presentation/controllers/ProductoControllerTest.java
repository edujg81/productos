package com.gedepsa.productos.presentation.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gedepsa.productos.business.model.Producto;
import com.gedepsa.productos.business.services.ProductoServices;

@WebMvcTest(ProductoController.class)
public class ProductoControllerTest {

	@Autowired
	private MockMvc miniPostman;
	
	@MockBean
	private ProductoServices productoServices;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	void obtenemos_todos_los_productos() throws Exception {
		
		Producto p1 = new Producto();
		Producto p2 = new Producto();
		
		p1.setCodigo(100L);
		p2.setCodigo(200L);
		
		List<Producto> productos = Arrays.asList(p1, p2);
		
		when(productoServices.getAll()).thenReturn(productos);
		
		MvcResult mvcResult = miniPostman.perform(get("/productos")).andReturn();
		
		int codigo = mvcResult.getResponse().getStatus();
		
		String strBody = mvcResult.getResponse().getContentAsString();
		String strBodyEsperado = objectMapper.writeValueAsString(productos);
		
		System.err.println("BODY QUE LLEGA ------------>     " + strBody);
		System.err.println("BODY ESPERADO  ------------>     " + strBodyEsperado);
		
		assertEquals(200, codigo);
		assertEquals(strBodyEsperado, strBody);
		
	}
	
}
