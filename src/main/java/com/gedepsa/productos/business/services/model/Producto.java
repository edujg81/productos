package com.gedepsa.productos.business.services.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="PRODUCTOS")
public class Producto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long codigo;
	
	private String nombre;
	private String descripcion;
	
	@Temporal(TemporalType.DATE)
	private Date fechaAlta;
	
	private double precio;
	
	@ManyToOne
	@JoinColumn(name="ID_CATEGORIA")
	private Categoria categoria;
	
	private boolean descatalogado;

	public Categoria getCategoria() {
		return this.categoria;
	}

	public Long getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public boolean getDescatalogado() {
		return descatalogado;
	}

	public void setDescatalogado(boolean descatalogado) {
		this.descatalogado = descatalogado;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}

//	public void setNombre(String nombre) {
//		this.nombre = nombre;
//	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
