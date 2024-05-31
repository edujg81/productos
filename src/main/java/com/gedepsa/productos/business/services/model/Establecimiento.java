package com.gedepsa.productos.business.services.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of="codigo")
@ToString
@Entity
@Table(name="ESTABLECIMIENTOS")
public class Establecimiento implements Serializable {
	
	@Id
	private Long codigo;
	
	@Column(name="NOMBRE_COMERCIAL")
	private String nombreComercial;

	@Embedded
	private Direccion direccion;
	
	@Column(name="DATOS_CONTACTO")
	@Embedded
	private DatosContacto datosContacto;
	
	@Column(name="FECHA_INAUGURACION")
	@Temporal(TemporalType.DATE)
	private Date fechaInauguracion;
	
}
