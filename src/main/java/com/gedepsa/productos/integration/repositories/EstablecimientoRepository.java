package com.gedepsa.productos.integration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gedepsa.productos.business.services.model.Establecimiento;

public interface EstablecimientoRepository extends JpaRepository<Establecimiento, Long> {

}
