package com.gedepsa.productos.business.model.dtos;

public class ProductoDTO2 {

	// nombre: 			Impresora HP 2D
	// descatalogado:	true
	// descripcion:     Producto que bla bla bla. 
	
	// El atributo producto debe tener el siguiente formato:
	
	// 1.- El nombre en mayúscula
	// 2.- Se incluye (*) si está descatalogado
	// 3.- La descripción se recorta a partir de 15 caracteres y se incluyen "..."
	
	// 
	
	private String producto; // IMPRESORA HP 2D (*) Producto que bl...

	public ProductoDTO2(String producto) {
		this.producto = producto;
	}

	public String getProducto() {
		return producto;
	}
	
}
