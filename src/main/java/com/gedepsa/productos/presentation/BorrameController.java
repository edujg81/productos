package com.gedepsa.productos.presentation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gedepsa.productos.business.model.Producto;
import com.gedepsa.productos.integration.repositories.ProductoRepository;

@RestController
public class BorrameController {

	@Autowired
	private ProductoRepository productoRepository;
	
	@GetMapping("/prueba")
	public String prueba() {
		
		// Vamos a obtener los productos!!
		
		List<Producto> productos = productoRepository.findAll();
		
		
		for(Producto producto: productos) {
			System.out.println(producto);
		}
		
		System.out.println("\n****************");
		
		Optional<Producto> optional = productoRepository.findById(3L);
		
		System.out.println(optional.get());
		
		System.out.println("\n****************");
		
		System.out.println(productoRepository.count());
		
		return "ok";
	}
	
}
