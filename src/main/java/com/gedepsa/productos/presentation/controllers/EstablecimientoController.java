package com.gedepsa.productos.presentation.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gedepsa.productos.business.services.EstablecimientoServices;
import com.gedepsa.productos.business.services.model.Establecimiento;

@RestController
@RequestMapping("/establecimientos")
public class EstablecimientoController {
	
private EstablecimientoServices establecimientoServices;
	
	public EstablecimientoController(EstablecimientoServices establecimientoServices) {
		this.establecimientoServices = establecimientoServices;
	}

	@GetMapping
	public List<Establecimiento> getAll() throws Exception{
		return establecimientoServices.getAll();
	}
}
