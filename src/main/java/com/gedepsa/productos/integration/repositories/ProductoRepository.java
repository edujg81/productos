package com.gedepsa.productos.integration.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gedepsa.productos.business.services.model.Categoria;
import com.gedepsa.productos.business.services.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{
	
	List<Producto> findByPrecioBetween(double min, double max);
	
	List<Producto> findByFechaAltaBetweenOrderByFechaAltaDesc(Date desde, Date hasta);
	
	List<Producto> findByDescatalogadoTrue();
	
	List<Producto> findByCategoria(Categoria categoria);
	
}
