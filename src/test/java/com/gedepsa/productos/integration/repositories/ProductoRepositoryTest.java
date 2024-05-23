package com.gedepsa.productos.integration.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import com.gedepsa.productos.business.model.Familia;
import com.gedepsa.productos.business.model.Producto;

@DataJpaTest
@Sql(scripts= {"/data/h2/schema.sql","/data/h2/data.sql"})
public class ProductoRepositoryTest {
	
	@Autowired
	private ProductoRepository productoRepository;

	@Test
	void findByPrecioBetweenOrderByPrecioTest() {
		
		double min = 125.3;
		double max = 250.3;
		
		Producto p1 = new Producto();
		Producto p2 = new Producto();
		Producto p3 = new Producto();
		
		p1.setCodigo(3L);
		p2.setCodigo(4L);
		p3.setCodigo(5L);
		
		List<Producto> productosEsperados = Arrays.asList(p1, p2, p3);
		
		List<Producto> productos = productoRepository.findByPrecioBetweenOrderByPrecio(min, max);
		
		assertEquals(3, productos.size());
		assertTrue(productos.containsAll(productosEsperados));
		
	}
	
	@Test
	void findByFamiliaTest() {
		
		Producto p1 = new Producto();
		Producto p2 = new Producto();
		
		p1.setCodigo(2L);
		p2.setCodigo(3L);
		
		List<Producto> productosEsperados = Arrays.asList(p1, p2);
		
		List<Producto> productos = productoRepository.findByFamilia(Familia.HARDWARE);
		
		assertEquals(2, productos.size());
		assertTrue(productos.containsAll(productosEsperados));
		
	}
	
	@Test
	void findByPrecioGreaterThanTest() {
		
		double min =  400.0;
		
		Producto p1 = new Producto();
		Producto p2 = new Producto();
		
		p1.setCodigo(2L);
		p2.setCodigo(7L);
		
		List<Producto> productosEsperados = Arrays.asList(p1, p2);
		
		List<Producto> productos = productoRepository.findByPrecioGreaterThan(min);
		
		assertEquals(2, productos.size());
		assertTrue(productos.containsAll(productosEsperados));
		
	}
	
}
