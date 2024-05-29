package com.gedepsa.productos.presentation.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gedepsa.productos.business.services.ProductoServices;
import com.gedepsa.productos.integration.repositories.ProductoRepository;

@RestController
public class PruebaController {

	@Autowired
	private ProductoServices productoServices;
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@GetMapping("/prueba2")
	public Map<String,Integer> getEstadistica1FromServicio() {
	
		return productoServices.getEstadisticaNumeroProductoPorCategoria();
	}
	
	@GetMapping("/prueba1")
	public List<Object[]> getEstadistica1FromRepositorio() {
	
		return productoRepository.getEstadisticaNumeroProductoPorCategoria();
	}
	
}
