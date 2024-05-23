package com.gedepsa.productos.business.services;

import java.util.List;
import java.util.Optional;

import com.gedepsa.productos.business.model.Familia;
import com.gedepsa.productos.business.model.Producto;
import com.gedepsa.productos.business.model.dtos.ProductoDTO1;
import com.gedepsa.productos.business.model.dtos.ProductoDTO2;

public interface ProductoServices {

	/**
	 * El producto ha de tener codigo NULL
	 * 
	 * Si el código no es NULL lanza IllegalStateException
	 * 
	 */
	Long create(Producto producto);  
	
	Optional<Producto> read(Long codigo);
	
	/**
	 * El producto ha de existir
	 * 
	 * Si el producto no existe lanza IllegalStateException
	 * 
	 */
	void update(Producto producto);
	
	/**
	 * 
	 * Si no existe el producto lanza IllegalStateException 
	 */
	void delete(Long codigo);
	
	List<Producto> getAll();
	
	/**
	 * 
	 * Incluye los extemos
	 * 
	 */
	List<Producto> getByPrecioBetween(double min, double max);
	
	List<Producto> getByFamilia(Familia familia);
	
	List<ProductoDTO1> getProductosDTO1();
	
	List<ProductoDTO2> getProductosDTO2();
	
	
}
