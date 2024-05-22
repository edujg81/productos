package com.gedepsa.productos.business.services;

import java.util.List;
import java.util.Optional;

import com.gedepsa.productos.business.model.Familia;
import com.gedepsa.productos.business.model.Producto;

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
	 * <h1 style="color:red;">SOY UN HEADER</h1>
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
	
	
}
