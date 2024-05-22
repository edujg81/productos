package com.gedepsa.productos.business.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.gedepsa.productos.business.model.Familia;
import com.gedepsa.productos.business.model.Producto;
import com.gedepsa.productos.business.services.ProductoServices;
import com.gedepsa.productos.integration.repositories.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
@Primary
public class ProductoServicesImpl implements ProductoServices{

	@Autowired
	private ProductoRepository productoRepository;
	
	@Override
	@Transactional
	public Long create(Producto producto) {
		
		if(producto.getCodigo() != null) {
			throw new IllegalStateException("Para crear un producto su código ha de ser null");
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
		
		boolean existe = productoRepository.existsById(codigo);
		
		if(!existe) {
			throw new IllegalStateException("El producto [" + codigo + "] no existe. No se puede actualizar");
		}
		
		productoRepository.save(producto);
	}

	@Override
	@Transactional
	public void delete(Long codigo) {

		boolean existe = productoRepository.existsById(codigo);
		
		if(!existe) {
			throw new IllegalStateException("El producto [" + codigo + "] no existe. No se puede eliminar");
		}
		
		productoRepository.deleteById(codigo);
		
	}

	@Override
	public List<Producto> getAll() {
		return productoRepository.findAll();
	}

	@Override
	public List<Producto> getByPrecioBetween(double min, double max) {
		// TODO
		return null;
	}

	@Override
	public List<Producto> getByFamilia(Familia familia) {
		// TODO
		return null;
	}
}
