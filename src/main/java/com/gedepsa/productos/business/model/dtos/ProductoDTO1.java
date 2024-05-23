package com.gedepsa.productos.business.model.dtos;

public class ProductoDTO1 {

	private String producto;
	private double precio;
	
	public ProductoDTO1(String producto, double precio) {
		this.producto = producto;
		this.precio = precio;
	}

	public String getProducto() {
		return producto;
	}

	public double getPrecio() {
		return precio;
	}
	
}
