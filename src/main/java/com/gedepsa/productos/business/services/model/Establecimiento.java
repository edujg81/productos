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
	
	private static final long serialVersionUID = 1L;
	private Long codigo;
	private String nombreComercial;
	private Direccion direccion;
	private DatosContacto datosContacto;
	private Date fechaInauguracion;
	
	public Long getCodigo() {
		return this.codigo;
	}
	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}	
	
	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public DatosContacto getDatosContacto() {
		return datosContacto;
	}

	public void setDatosContacto(DatosContacto datosContacto) {
		this.datosContacto = datosContacto;
	}

	public Date getFechaInauguracion() {
		return fechaInauguracion;
	}

	public void setFechaInauguracion(Date fechaInauguracion) {
		this.fechaInauguracion = fechaInauguracion;
	}
}
