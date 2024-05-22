package com.gedepsa.productos.integration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gedepsa.productos.business.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{

}
