package com.gedepsa.productos.integration.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import java.util.Arrays;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import com.gedepsa.productos.business.services.model.Producto;

@DataJpaTest
@Sql(scripts={"/data/h2/schema_test.sql", "/data/h2/data_test.sql"})
public class ProductoRepositoryTest {

	@Autowired
	private ProductoRepository productoRepository;
	
	@Test
	void findByPrecioBetweenTest() {
		
		Producto p1 = new Producto();
		Producto p2 = new Producto();
		Producto p3 = new Producto();
		Producto p4 = new Producto();
		
		p1.setCodigo(107L);
		p2.setCodigo(108L);
		p3.setCodigo(110L);
		p4.setCodigo(111L);
		
		List<Producto> productos = productoRepository.findByPrecioBetween(7.0, 8.0);
		
		assertEquals(4, productos.size());
		assertTrue(productos.containsAll(Arrays.asList(p1, p2, p3, p4)));
		
	}
	
	@Test
	@Disabled
	void findByFechaAltaBetweenOrderByFechaAltaDescTest() {
		fail("Not implemented yet!");
	}
	
	@Test
	@Disabled
	void findByDescatalogadoTrueTest() {
		fail("Not implemented yet!");
	}
	
	@Test
	@Disabled
	void findByCategoriaTest() {
		fail("Not implemented yet!");	
	}
	 
}
