package com.gedepsa.productos.business.services.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DatosContacto implements Serializable {

	private String telefono;
	private String fax;
	private String email;
	
}
