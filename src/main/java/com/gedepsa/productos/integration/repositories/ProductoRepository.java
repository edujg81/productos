package com.gedepsa.productos.integration.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gedepsa.productos.business.model.Familia;
import com.gedepsa.productos.business.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{

	List<Producto> findByPrecioBetweenOrderByPrecio(double min, double max);
	
	List<Producto> findByFamilia(Familia familia);
	
	List<Producto> findByPrecioGreaterThan(double precio);
	
}
