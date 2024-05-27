package com.gedepsa.productos.business.services.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of="id")
@ToString
@Entity
@Table(name="CATEGORIAS")
public class Categoria implements Serializable {
	
	@Id
	private Long id;
	
	@Column(name="NAME")
	private String nombre;
	
}
