package com.gedepsa.productos.presentation.config;

import org.springframework.http.HttpStatus;

public class PresentationException extends RuntimeException{

	private HttpStatus httpStatus;
	
	public PresentationException(String mensaje, HttpStatus httpStatus) {
		super(mensaje);
		this.httpStatus = httpStatus;
	}
	
	public PresentationException(String mensaje, int httpStatusCode) {
		super(mensaje);
		this.httpStatus = HttpStatus.valueOf(httpStatusCode);
	}
	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	
}
