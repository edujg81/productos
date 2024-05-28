package com.gedepsa.productos.presentation.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.gedepsa.productos.business.services.ProductoServices;
import com.gedepsa.productos.business.services.model.Producto;
import com.gedepsa.productos.presentation.config.PresentationException;
import com.gedepsa.productos.rabbitmq.Publisher;

@RestController
@RequestMapping("/productos")
public class ProductoController {
	
	private ProductoServices productoServices;
	private Publisher publisher;
	
	public ProductoController(Publisher publisher, ProductoServices productoServices) {
		this.productoServices = productoServices;
		this.publisher = publisher;
	}
		
	@GetMapping
	public List<Producto> getAll(@RequestParam(required = false) Double min, 
			                     @RequestParam(required = false) Double max){
		
		List<Producto> productos;
		
		if(min != null & max != null) {
			productos = productoServices.getBetweenPriceRange(min, max);
		} else {
			productos = productoServices.getAll();
		}
		
		return productos;
	}
	
	@GetMapping("/{codigo}")
	public Producto getByCodigo(@PathVariable Long codigo) {
		
		Optional<Producto> optional = productoServices.read(codigo);
		
		if(optional.isEmpty()) {	 
			throw new PresentationException("No existe el producto con código [" + codigo + "]", HttpStatus.NOT_FOUND);
		}
		
		return optional.get();
	}

	@PostMapping
	public ResponseEntity<Object> create(@RequestBody Producto producto, UriComponentsBuilder ucb) {
		
		Long nuevoCodigo = null;
		
		try {
			nuevoCodigo = productoServices.create(producto);
			
			publisher.send("Se ha creado el producto [" + nuevoCodigo + "] de la categoria [" + (producto.getCategoria()).getId() + "]");
			
		} catch(IllegalStateException e) {
			throw new PresentationException(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
			
		return ResponseEntity.created(ucb.path("/productos/{codigo}").build(nuevoCodigo))
								.build();
	}
	
	@PutMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@RequestBody Producto producto, @PathVariable Long codigo) {
		
		producto.setCodigo(codigo);
		
		try {
			productoServices.update(producto);
		} catch(IllegalStateException e) {
			throw new PresentationException(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long codigo) {
		
		try {
			productoServices.delete(codigo);
		} catch(IllegalStateException e) {
			throw new PresentationException(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
		
}
