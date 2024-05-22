package com.gedepsa.productos.integration.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gedepsa.productos.business.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{

	List<Producto> findByPrecioBetweenOrderByPrecio(double min, double max);
	
	// JPQL Java Persistence Query Language
	
	@Query("SELECT p FROM Producto p WHERE p.precio > :precio")
	List<Producto> dameProductos(double precio);
	
	// SQL
	
	@Query(value="SELECT * FROM PRODUCTOS p WHERE p.precio > :precio", nativeQuery = true)
	List<Producto> dameProductosDeFormaNativa(double precio);

}
