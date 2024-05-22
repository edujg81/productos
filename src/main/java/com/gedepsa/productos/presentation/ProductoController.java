package com.gedepsa.productos.presentation;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.gedepsa.productos.business.model.Producto;
import com.gedepsa.productos.business.services.ProductoServices;

@RestController
public class ProductoController {

	@Autowired
	private ProductoServices productoServices;
	
	@GetMapping("/productos")
	public List<Producto> getProductos(){
		return productoServices.getAll();
	}
		
	@GetMapping("/productos/{codigo}")
	public ResponseEntity<?> read(@PathVariable Long codigo) {
		
		Optional<Producto> optional = productoServices.read(codigo);
		
		if(optional.isEmpty()) {
			
			CustomHttpErrorMessage respuesta = new CustomHttpErrorMessage("NO EXISTE EL PRODUCTO " + codigo);
			
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(respuesta);
		}
		
		return ResponseEntity.ok(optional.get());
	}
	
	@PostMapping("/productos")
	public ResponseEntity<?> create(@RequestBody Producto producto, UriComponentsBuilder ucb) {
		
		Long codigo = null;
		
		try {
			codigo = productoServices.create(producto);
		} catch(IllegalStateException e) {
			CustomHttpErrorMessage respuesta = new CustomHttpErrorMessage(e.getMessage());
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(respuesta);
		}
				
		URI uri = ucb.path("/productos/{codigo}").build(codigo);
		
		return ResponseEntity.created(uri).build();	
	}
	
}
