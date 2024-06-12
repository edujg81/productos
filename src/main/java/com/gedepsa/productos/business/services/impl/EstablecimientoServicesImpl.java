package com.gedepsa.productos.business.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gedepsa.productos.business.services.EstablecimientoServices;
import com.gedepsa.productos.business.services.model.Establecimiento;

@Service
public class EstablecimientoServicesImpl implements EstablecimientoServices{

	@Override
	public Long create(Establecimiento establecimiento) {
		// TODO Esbozo de método generado automáticamente
		return null;
	}

	@Override
	public Optional<Establecimiento> read(Long codigo) {
		// TODO Esbozo de método generado automáticamente
		return Optional.empty();
	}

	@Override
	public List<Establecimiento> getAll() {
		// TODO Esbozo de método generado automáticamente
		return null;
	}

}
