package com.gedepsa.productos.business.services;

import java.util.List;
import java.util.Optional;

import com.gedepsa.productos.business.services.model.Categoria;

public interface CategoriaServices {

	/**
	 * Devuelve el id que le ha otorgado el sistema a la nueva categoria
	 * 
	 * Si el id de la categoria NO es null lanza IllegalStateException
	 * 
	 */
	Long create(Categoria categoria);
	
	Optional<Categoria> read(Long id);
	
	List<Categoria> getAll();
}
