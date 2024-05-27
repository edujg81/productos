package com.gedepsa.productos.presentation.config;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GestorCentralizadoExcepciones extends ResponseEntityExceptionHandler{
	
	//private Logger logger = LoggerFactory.getLogger(GestorCentralizadoExcepciones.class);

	// **************************************************************************************
	//
	// Métodos para gestionar excepciones específicas
	//
	// **************************************************************************************
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request){
		
		//logger.error("Application error in: [" + ex.getClass().getName() + "]", ex);
		
		return handleExceptionInternal(ex, new RespuestaError(ex.getMessage()), null, HttpStatus.INTERNAL_SERVER_ERROR, request);
		
	}
	
	// **************************************************************************************
	
	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<Object> handleIllegalStateException(IllegalStateException ex, WebRequest request){
		
		//logger.error("Application error in: [" + ex.getClass().getName() + "]", ex);
				
		return handleExceptionInternal(ex, new RespuestaError(ex.getMessage()), null, HttpStatus.BAD_REQUEST, request);
		
	}
	
	// **************************************************************************************
	
	@ExceptionHandler(PresentationException.class)
	public ResponseEntity<Object> handlePresentationException(PresentationException ex, WebRequest request){
		
		//logger.error("Application error in: [" + ex.getClass().getName() + "]", ex);
		
		RespuestaError respuestaError = new RespuestaError(ex.getMessage());
		
		return handleExceptionInternal(ex, respuestaError, null, ex.getHttpStatus(), request);
		
	}

	// **************************************************************************************
	//
	// Métodos reimplementados de la superclase
	//
	// **************************************************************************************

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		String tipoEntrante = ex.getValue().getClass().getSimpleName();
		String tipoRequerido = ex.getRequiredType().getSimpleName();
		
		String mensaje = "El valor " + ex.getValue() + " es del tipo [" + tipoEntrante + "]. Se requeria un valor de tipo ["+ tipoRequerido + "]";
		
		//logger.error("Application error in: [" + ex.getClass().getName() + "]", ex);
		
		return handleExceptionInternal(ex, new RespuestaError(mensaje), headers, HttpStatus.BAD_REQUEST, request);
		
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		// TODO Explicar esto!
		
		//logger.error("Application error in: [" + ex.getClass().getName() + "]", ex);
		
		return handleExceptionInternal(ex, "Oye! Muy mal. Falta la variable en el path!", headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		//logger.error("Application error in: [" + ex.getClass().getName() + "]", ex);
		
		return handleExceptionInternal(ex, new RespuestaError("Falta aportar algún recurso en la petición..."), headers, status, request);
	}
		
}
