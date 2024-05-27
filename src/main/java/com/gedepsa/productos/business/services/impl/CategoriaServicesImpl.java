package com.gedepsa.productos.business.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gedepsa.productos.business.services.CategoriaServices;
import com.gedepsa.productos.business.services.model.Categoria;
import com.gedepsa.productos.integration.repositories.CategoriaRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoriaServicesImpl implements CategoriaServices {

	private CategoriaRepository categoriaRepository;
	
	public CategoriaServicesImpl(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}

	@Override
	@Transactional
	public Long create(Categoria categoria) {
		
		if(categoria.getId() != null) {
			throw new IllegalStateException("Para crear una categoria su id debe ser null");
		}
		
		Long id = System.currentTimeMillis();
		categoria.setId(id);
		
		categoriaRepository.save(categoria);
		
		return id;
	}

	@Override
	public Optional<Categoria> read(Long id) {
		return categoriaRepository.findById(id);
	}

	@Override
	public List<Categoria> getAll() {
		return categoriaRepository.findAll();
	}

}
