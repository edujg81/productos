package com.gedepsa.productos.business.services.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gedepsa.productos.business.services.ProductoServices;
import com.gedepsa.productos.business.services.model.Categoria;
import com.gedepsa.productos.business.services.model.Producto;
import com.gedepsa.productos.integration.repositories.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductoServicesImpl implements ProductoServices {
	
	private ProductoRepository productoRepository;
	
	public ProductoServicesImpl(ProductoRepository productoRepository) {
		this.productoRepository = productoRepository;
	}
	
	@Override
	@Transactional
	public Long create(Producto producto) {
		
		if(producto.getCodigo() != null) {
			throw new IllegalStateException("Para crear un producto su código debe ser NULL");
		}
		
		Long codigo = System.currentTimeMillis();
		producto.setCodigo(codigo);
		
		productoRepository.save(producto);
		
		return codigo;
	}

	@Override
	public Optional<Producto> read(Long codigo) {
		return productoRepository.findById(codigo);
	}

	@Override
	@Transactional
	public void update(Producto producto) {
		
		Long codigo = producto.getCodigo();
		
		if (codigo == null) {
			throw new IllegalStateException("No se puede actualizar un producto con codigo NULL");
		}
		
		boolean existe = productoRepository.existsById(codigo);
		
		if(!existe) {
			throw new IllegalStateException("El producto con codigo " +  codigo + " no existe. No se puede actualizar");
		}
		
		productoRepository.save(producto);
		
	}
	
	@Override
	@Transactional
	public void delete(Long codigo) {
				
		if (codigo == null) {
			throw new IllegalStateException("No se puede eliminar un producto con codigo NULL");
		}
		
		boolean existe = productoRepository.existsById(codigo);
		
		if(!existe) {
			throw new IllegalStateException("El producto con codigo " +  codigo + " no existe. No se puede eliminar");
		}
		
		productoRepository.deleteById(codigo);
		
	}

	@Override
	public List<Producto> getAll() {
		return productoRepository.findAll();
	}

	@Override
	public List<Producto> getBetweenPriceRange(double min, double max) {
		return productoRepository.findByPrecioBetween(min, max);
	}
	
	@Override
	public List<Producto> getBetweenDates(Date desde, Date hasta) {
		return productoRepository.findByFechaAltaBetweenOrderByFechaAltaDesc(desde, hasta);
	}
	
	@Override
	public List<Producto> getDescatalogados() {
		return productoRepository.findByDescatalogadoTrue();
	}
	
	@Override
	public List<Producto> getByCategoria(Categoria categoria) {
		return productoRepository.findByCategoria(categoria);
	}

	@Override
	public int getNumeroTotalProductos() {
		return (int) productoRepository.count();
	}

	@Override
	@Transactional
	public void variarPrecio(List<Producto> productos, double porcentaje) {
		productoRepository.variarPrecio(productos, porcentaje);
	}

	@Override
	@Transactional
	public void variarPrecio(long[] codigos, double porcentaje) {
		productoRepository.variarPrecio(codigos, porcentaje);
	}

	@Override
	public Map<String, Integer> getEstadisticaNumeroProductoPorCategoria() {
			
		Map<String, Integer> estadistica = new HashMap<>();
		
		for(Object[] fila: productoRepository.getEstadisticaNumeroProductoPorCategoria()) {
			String nombreCategoria = (String) fila[0];
			Integer numeroProductos = ((Long) fila[1]).intValue();
			estadistica.put(nombreCategoria, numeroProductos);
		}
		
		return estadistica;
	}
	
	@Override
	public Map<String, Double> getEstadisticaPrecioMedioProductosPorCategoria() {
		// TODO
		return null;
	}
	
}
