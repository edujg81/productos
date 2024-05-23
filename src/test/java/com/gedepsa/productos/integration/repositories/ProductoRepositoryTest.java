package com.gedepsa.productos.integration.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@Sql(scripts= {"/data/h2/schema.sql","/data/h2/data.sql"})
public class ProductoRepositoryTest {
	
	@Autowired
	private ProductoRepository productoRepository;

	@Test
	void findByPrecioBetweenOrderByPrecioTest() {
		fail("Not implemented yet!");
	}
	
	@Test
	void findByFamiliaTest() {
		fail("Not implemented yet!");
	}
	
	@Test
	void dameProductosTest() {
		fail("Not implemented yet!");
	}
	
	@Test
	void dameProductosDeFormaNativaTest() {
		fail("Not implemented yet!");
	}

}
