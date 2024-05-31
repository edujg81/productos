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
public class DatosContacto implements Serializable {

	private String telefono;
	private String fax;
	private String email;
	
}
