package com.gedepsa.productos.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gedepsa.productos.business.model.dtos.ProductoDTO1;
import com.gedepsa.productos.business.services.ProductoServices;
import com.gedepsa.productos.integration.repositories.ProductoRepository;

@RestController
public class BorrameController {

	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private ProductoServices productoServices;
/*	
	@GetMapping("/resultset")
	public List<Object[]> getResultSetDTO1() {
		return productoRepository.findProductoDTO1();
	}
*/	
	@GetMapping("/dtos")
	public List<ProductoDTO1> getProductosDTO1(){
		return productoServices.getProductosDTO1();
	}
	
}
