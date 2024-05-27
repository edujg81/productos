package com.gedepsa.productos.presentation.config;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RespuestaError {

	private String error;
	
	public RespuestaError(String error) {
		this.error = error;
	}
	
}
