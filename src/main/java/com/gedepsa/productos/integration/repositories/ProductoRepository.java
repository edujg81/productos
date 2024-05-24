package com.gedepsa.productos.integration.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.gedepsa.productos.business.model.Familia;
import com.gedepsa.productos.business.model.Producto;
import com.gedepsa.productos.business.model.dtos.ProductoDTO1;
import com.gedepsa.productos.business.model.dtos.ProductoDTO2;

public interface ProductoRepository extends JpaRepository<Producto, Long>{

	List<Producto> findByPrecioBetweenOrderByPrecio(double min, double max);
	
	List<Producto> findByFamilia(Familia familia);
	
	List<Producto> findByPrecioGreaterThan(double precio);

	@Query("SELECT CONCAT(UPPER(p.nombre), ' [',p.familia,']'), p.precio FROM Producto p")
	List<Object[]> findProductoDTO1Precocinado();
	
	@Query("SELECT new com.gedepsa.productos.business.model.dtos.ProductoDTO1(CONCAT(UPPER(p.nombre),' [',p.familia,']'), p.precio) FROM Producto p")
	List<ProductoDTO1> findProductoDTO1();
	
	
	@Query("SELECT new com.gedepsa.productos.business.model.dtos.ProductoDTO2("
		 + "        CONCAT(UPPER(p.nombre), ' ', "
			
	     + "CASE WHEN LENGTH(p.descripcion) > 15 THEN CONCAT(LEFT(p.descripcion, 15), '...') ELSE p.descripcion END, "
			+ "CASE WHEN p.descatalogado THEN ' (*)' ELSE '' END)) " 
			
			+ "FROM Producto p")
	List<ProductoDTO2> findProductoDTO2();
	
	@Query("UPDATE Producto p SET p.precio = p.precio - (p.precio * :descuento / 100) WHERE p.descatalogado IS TRUE")
	@Modifying
	int aplicarDescuento(double descuento);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
