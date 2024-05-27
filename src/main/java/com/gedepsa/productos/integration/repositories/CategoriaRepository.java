package com.gedepsa.productos.integration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gedepsa.productos.business.services.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {


}
