package com.gedepsa.productos.business.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gedepsa.productos.business.model.Familia;
import com.gedepsa.productos.business.model.Producto;
import com.gedepsa.productos.business.services.ProductoServices;

@Service
public class ProductoServicesDummyImpl implements ProductoServices{

	private final Map<Long, Producto> PRODUCTOS = new HashMap<>();
	
	public ProductoServicesDummyImpl() {
		initMap();
	}
	
	@Override
	public Long create(Producto producto) {
		
		if(producto.getCodigo() != null) {
			throw new IllegalStateException("Para crear un producto su código ha de ser null");
		}
		
		Long codigo = System.currentTimeMillis();
		
		producto.setCodigo(codigo);
		
		PRODUCTOS.put(codigo, producto);
		
		return codigo;
	}

	@Override
	public Optional<Producto> read(Long codigo) {
		return Optional.ofNullable(PRODUCTOS.get(codigo));
	}

	@Override
	public void update(Producto producto) {
		
		Long codigo = producto.getCodigo();
		
		boolean existe = PRODUCTOS.containsKey(codigo);
		
		if(!existe) {
			throw new IllegalStateException("El producto [" + codigo + "] no existe. No se puede actualizar");
		}
		
		PRODUCTOS.replace(codigo, producto);
			
	}

	@Override
	public void delete(Long codigo) {
		
		boolean existe = PRODUCTOS.containsKey(codigo);
		
		if(!existe) {
			throw new IllegalStateException("El producto [" + codigo + "] no existe. No se puede eliminar");
		}
		
		PRODUCTOS.remove(codigo);
		
	}

	@Override
	public List<Producto> getAll() {
		return new ArrayList<>(PRODUCTOS.values());
	}

	@Override
	public List<Producto> getByPrecioBetween(double min, double max) {
		
		List<Producto> productos = new ArrayList<>();
		
		for(Producto producto: PRODUCTOS.values()) {
			if(producto.getPrecio() >= min && producto.getPrecio() <= max) {
				productos.add(producto);
			}
		}
		
		return productos;
	}

	@Override
	public List<Producto> getByFamilia(Familia familia) {

		List<Producto> productos = new ArrayList<>();
		
		for(Producto producto: PRODUCTOS.values()) {
			if(producto.getFamilia().equals(familia)) {
				productos.add(producto);
			}
		}
		
		return productos;
		
	}
	
	// *******************************************************
	//
	// Private Methods 
	//
	// *******************************************************
	
	private void initMap() {
		
		Producto p1 = new Producto();
		Producto p2 = new Producto();
		Producto p3 = new Producto();
		
		p1.setCodigo(100L);
		p1.setNombre("Alfombrilla Mouse Cristiano Ronaldo");
		p1.setPrecio(0.50);
		p1.setDescripcion("Alfombrilla de buena calidad...");
		p1.setFamilia(Familia.CONSUMIBLE);
		p1.setFechaAlta(new Date());
		p1.setDescatalogado(true);
		
		p2.setCodigo(101L);
		p2.setNombre("Impresora HP 2P plus");
		p2.setPrecio(450.7);
		p2.setDescripcion("Impresira de alta calidad...");
		p2.setFamilia(Familia.HARDWARE);
		p2.setFechaAlta(new Date());
		p2.setDescatalogado(false);
		
		p3.setCodigo(102L);
		p3.setNombre("Ordenador Epson D20");
		p3.setPrecio(790.0);
		p3.setDescripcion("Equipo Desktop de alta robustez...");
		p3.setFamilia(Familia.HARDWARE);
		p3.setFechaAlta(new Date());
		p3.setDescatalogado(false);
		
		PRODUCTOS.put(p1.getCodigo(), p1);
		PRODUCTOS.put(p2.getCodigo(), p2);
		PRODUCTOS.put(p3.getCodigo(), p3);	
	}

}
