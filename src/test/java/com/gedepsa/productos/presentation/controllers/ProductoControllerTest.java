package com.gedepsa.productos.presentation.controllers;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MvcResult;

import com.gedepsa.productos.business.services.ProductoServices;
import com.gedepsa.productos.business.services.model.Categoria;
import com.gedepsa.productos.business.services.model.Producto;
import com.gedepsa.productos.presentation.config.RespuestaError;

@WebMvcTest(value=ProductoController.class)
class ProductoControllerTest extends AbstractControllerTest{
	
	@MockBean
	private ProductoServices productoServices;
	
	private Producto producto1;
	private Producto producto2;
	
	@BeforeEach
	void init() {
		initObjects();
	}
	
	@Test
	void solicitamos_todos_los_productos() throws Exception {
		
		List<Producto> productos = Arrays.asList(producto1, producto2);
		
		when(productoServices.getAll()).thenReturn(productos);
		
		MvcResult mvcResult = mockMvc.perform(get("/productos")).andExpect(status().isOk()).andReturn();
				
		checkResponseBody(productos, mvcResult);
		
	}
	
	@Test
	void solicitamos_producto_EXISTENTE_a_partir_de_su_codigo() throws Exception {
			
		when(productoServices.read(100L)).thenReturn(Optional.of(producto1));
		
		MvcResult mvcResult = mockMvc.perform(get("/productos/100")).andExpect(status().isOk()).andReturn();
		
		checkResponseBody(producto1, mvcResult);
		
	}
	
	@Test
	void solicitamos_producto_NO_EXISTENTE_a_partir_de_su_codigo() throws Exception {
			
		when(productoServices.read(100L)).thenReturn(Optional.empty());
		
		MvcResult mvcResult = mockMvc.perform(get("/productos/100")).andExpect(status().isNotFound()).andReturn();
			
		checkResponseBody(new RespuestaError("No existe el producto con código [100]"), mvcResult);
	}
	
	@Test
	void creamos_producto_ok() throws Exception {

		producto1.setCodigo(null);

		when(productoServices.create(producto1)).thenReturn(1011L);

		String requestBody = objectMapper.writeValueAsString(producto1);

		mockMvc.perform(post("/productos").content(requestBody).contentType("application/json"))
				.andExpect(status().isCreated())
				.andExpect(header().string("Location", "http://localhost/productos/1011"));
	}

	@Test
	void creamos_producto_con_codigo_NO_null() throws Exception {
			
		when(productoServices.create(producto1)).thenThrow(new IllegalStateException("El mensaje de turno!"));
	
		String requestBody = objectMapper.writeValueAsString(producto1);
		
		MvcResult respuesta = mockMvc.perform(post("/productos").content(requestBody).contentType("application/json"))
													.andExpect(status().isBadRequest())
													.andReturn();
	
		checkResponseBody(new RespuestaError("El mensaje de turno!"), respuesta);
	}

	@Test
	void actualizamos_producto_ok() throws Exception {

		String requestBody = objectMapper.writeValueAsString(producto1);

		mockMvc.perform(put("/productos/100").content(requestBody).contentType("application/json"))
				.andExpect(status().isNoContent());

		verify(productoServices, times(1)).update(producto1);
	}

	@Test
	void actualizamos_producto_con_codigo_null() throws Exception {

		doThrow(new IllegalStateException("EL MENSAJE")).when(productoServices).update(producto1);

		String requestBody = objectMapper.writeValueAsString(producto1);

		MvcResult respuesta = mockMvc
				.perform(put("/productos/100").content(requestBody).contentType("application/json"))
				.andExpect(status().isNotFound()).andReturn();

		verify(productoServices, times(1)).update(producto1);

		checkResponseBody(new RespuestaError("EL MENSAJE"), respuesta);
	}
	
	@Test
	void eliminamos_producto_OK() throws Exception {

		mockMvc.perform(delete("/productos/100")).andExpect(status().isNoContent());

		verify(productoServices, times(1)).delete(100L);

	}
	
	@Test
	void eliminamos_producto_NO_EXISTENTE() throws Exception {

		doThrow(new IllegalStateException("EL MENSAJE")).when(productoServices).delete(100L);

		MvcResult respuesta = mockMvc.perform(delete("/productos/100")).andExpect(status().isNotFound()).andReturn();

		verify(productoServices, times(1)).delete(100L);

		checkResponseBody(new RespuestaError("EL MENSAJE"), respuesta);
	}
	
	// *************************************************
	//
	// Private Methods
	//
	// *************************************************

	private void initObjects() {

		Date fecha1 = null;
		Date fecha2 = null;

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			fecha1 = sdf.parse("15/06/2022");
			fecha2 = sdf.parse("23/11/2023");
		} catch (Exception e) {
			throw new RuntimeException();
		}

		Categoria c1 = new Categoria();
		Categoria c2 = new Categoria();

		c1.setId(1L);
		c1.setNombre("VEGANO");

		c2.setId(2L);
		c2.setNombre("SIDRA");

		producto1 = new Producto();
		producto2 = new Producto();

		producto1.setCodigo(100L);
		producto1.setNombre("Tortilla Vegana");
		producto1.setDescripcion("Descripción Tortilla Vegana");
		producto1.setPrecio(10.20);
		producto1.setCategoria(c1);
		producto1.setFechaAlta(fecha1);
		producto1.setDescatalogado(false);

		producto2.setCodigo(101L);
		producto2.setNombre("El Gaitero");
		producto2.setDescripcion("Descripción El Gaitero");
		producto2.setPrecio(8.10);
		producto2.setCategoria(c2);
		producto2.setFechaAlta(fecha2);
		producto2.setDescatalogado(true);
	}
	
}
