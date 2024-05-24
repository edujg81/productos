package com.gedepsa.productos.presentation.pruebas;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gedepsa.productos.business.model.Producto;
import com.gedepsa.productos.integration.repositories.ProductoRepository;

import jakarta.transaction.Transactional;

@RestController
public class PruebasController {

	@Autowired
	private ProductoRepository productoRepository;
	
	// http://localhost:8080/aplicar-descuento?descuento=20.0
	
	@GetMapping("/aplicar-descuento")
	@Transactional
	public int actualizarPrecios(@RequestParam Double descuento) {
			
		int numeroActualizaciones = productoRepository.aplicarDescuento(descuento);
		
		Optional<Producto> optional = productoRepository.findById(7L);  
		
		System.out.println("Precio del producto 7 después del flush(): " + optional.get().getPrecio());  // muestra el valor actualizado
		
		System.err.println("Vamos a iniciar siesta... Cuando esto acabe se hará efectiva la actualización de " + numeroActualizaciones);
		
		try {
			Thread.sleep(25000);
		} catch (InterruptedException e) {
			
		}
		
		System.err.println("siesta completada");
	
		// System.out.println(10 / 0); // bomba que provoca un rollback (no hay consolidación)
		
		return numeroActualizaciones;
	}
	
	@GetMapping("/maxint")
	public int dameIntMaximo() {
		return Integer.MAX_VALUE;
	}
}
