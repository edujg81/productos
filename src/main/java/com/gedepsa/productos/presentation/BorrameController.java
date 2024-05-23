package com.gedepsa.productos.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gedepsa.productos.business.model.dtos.ProductoDTO1;
import com.gedepsa.productos.business.model.dtos.ProductoDTO2;
import com.gedepsa.productos.business.services.ProductoServices;
import com.gedepsa.productos.integration.repositories.ProductoRepository;

@RestController
public class BorrameController {

	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private ProductoServices productoServices;
	
	// PROBAMOS REPOSITORIOS
	
	@GetMapping("/dto1Precocinado")
	public List<Object[]> getResultSetDTO1() {
		return productoRepository.findProductoDTO1Precocinado();
	}
	
	/*
	@GetMapping("/dto2Precocinado")
	public List<Object[]> getResultSetDTO2() {
		return productoRepository.findProductoDTO2Precocinado();
	}
	*/
	
	// PROBAMOS SERVICIOS
	
	@GetMapping("/dtos1")
	public List<ProductoDTO1> getProductosDTO1(){
		return productoServices.getProductosDTO1();
	}
	
	@GetMapping("/dtos2")
	public List<ProductoDTO2> getProductosDTO2(){
		return productoServices.getProductosDTO2();
	}
	
}
