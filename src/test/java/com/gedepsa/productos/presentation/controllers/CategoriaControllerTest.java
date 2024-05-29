package com.gedepsa.productos.presentation.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MvcResult;

import com.gedepsa.productos.business.services.CategoriaServices;
import com.gedepsa.productos.business.services.model.Categoria;
import com.gedepsa.productos.presentation.config.RespuestaError;

@WebMvcTest(value=CategoriaController.class)
class CategoriaControllerTest extends AbstractControllerTest{
	
	@MockBean
	private CategoriaServices categoriaServices;
	
	private Categoria categoria1;
	private Categoria categoria2;
	
	@BeforeEach
	void init() {
		initObjects();
	}
	
	@Test
	void solicitamos_todas_las_categorias() throws Exception {
		
		// Arrange
		
		Categoria categoria1 = new Categoria();
		Categoria categoria2 = new Categoria();
		
		categoria1.setId(100L);
		categoria1.setNombre("REFRESCOS");
		
		categoria2.setId(101L);
		categoria2.setNombre("LICORES");
		
		List<Categoria> categorias = Arrays.asList(categoria1, categoria2);
		
		when(categoriaServices.getAll()).thenReturn(categorias);
		
		// Act
		
		MvcResult mvcResult = mockMvc.perform(get("/categorias")).andReturn();
		
		// Assert
		
		assertEquals(200, mvcResult.getResponse().getStatus());  // Comprobamos el codigo HTTPStatus de la respuesta
		
		checkResponseBody(categorias, mvcResult); 				// Comprobamos el contenido del body
		         	
	}
	
	@Test
	void solicitamos_categoria_EXISTENTE_a_partir_de_su_id() throws Exception {
		
		when(categoriaServices.read(100L)).thenReturn(Optional.of(categoria1));
		
		MvcResult respuesta = mockMvc.perform(get("/categorias/100"))
											.andExpect(status().isOk())
											.andReturn();
		
		checkResponseBody(categoria1, respuesta);
	}

	@Test
	void solicitamos_categoria_NO_EXISTENTE_a_partir_de_su_id() throws Exception {
		
		when(categoriaServices.read(100L)).thenReturn(Optional.empty());
		
		MvcResult respuesta = mockMvc.perform(get("/categorias/100"))
											.andExpect(status().isNotFound())
											.andReturn();
		
		checkResponseBody(new RespuestaError("No existe la categoria con id [100]"), respuesta);
	}

	@Test
	void creamos_categoria_ok() throws Exception {

		categoria1.setId(null);

		when(categoriaServices.create(categoria1)).thenReturn(467L);

		String requestBody = objectMapper.writeValueAsString(categoria1);

		mockMvc.perform(post("/categorias").content(requestBody).contentType("application/json"))
				.andExpect(status().isCreated())
				.andExpect(header().string("Location", "http://localhost/categorias/467"));
	}

	@Test
	void creamos_categoria_con_id_NO_null() throws Exception {
			
		when(categoriaServices.create(categoria1)).thenThrow(new IllegalStateException("EL MENSAJE"));
	
		String requestBody = objectMapper.writeValueAsString(categoria1);
		
		MvcResult respuesta = mockMvc.perform(post("/categorias").content(requestBody).contentType("application/json"))
													.andExpect(status().isBadRequest())
													.andReturn();
	
		checkResponseBody(new RespuestaError("EL MENSAJE"), respuesta);
	}

	// *************************************************
	//
	// Private Methods
	//
	// *************************************************

	private void initObjects() {

		categoria1 = new Categoria();
		categoria1.setId(100L);
		categoria1.setNombre("TAPAS");

		categoria2 = new Categoria();
		categoria2.setId(101L);
		categoria2.setNombre("REFRESCOS");
	}
	
}
