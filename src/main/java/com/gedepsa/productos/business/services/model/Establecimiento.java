package com.gedepsa.productos.business.services.model;

import java.io.Serializable;
import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of="codigo")
@ToString
public class Establecimiento implements Serializable {
	
	private Long codigo;
	private String nombreComercial;
	private Direccion direccion;
	private DatosContacto datosContacto;
	private Date fechaInauguracion;
	
}
