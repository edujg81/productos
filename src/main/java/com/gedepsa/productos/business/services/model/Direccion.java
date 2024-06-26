package com.gedepsa.productos.business.services.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Embeddable
public class Direccion implements Serializable {

	private String direccion;
	private String poblacion;
	private String codigoPostal;
	private String provincia;
	private String pais;
	
}
