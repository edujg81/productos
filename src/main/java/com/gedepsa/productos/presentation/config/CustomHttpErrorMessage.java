package com.gedepsa.productos.presentation.config;

public class CustomHttpErrorMessage {

	private String error;
	private Long timeStamp;
	
	public CustomHttpErrorMessage(String error) {
		this.error = error;
		this.timeStamp = System.currentTimeMillis();
	}

	public String getError() {
		return error;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}
	
}
