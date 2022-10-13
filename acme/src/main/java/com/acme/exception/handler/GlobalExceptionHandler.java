package com.acme.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.acme.exception.CoverageAlreadyExistsException;
import com.acme.exception.CoverageNotFoundException;
import com.acme.exception.ProductAlreadyExistsException;
import com.acme.exception.ProductNotFoundException;
import com.acme.exception.ProspectAlreadyExistsException;
import com.acme.exception.ProspectNotFoundException;
import com.acme.exception.SimulationNotFoundException;
import com.acme.exception.SimulationProductCoveragesException;
import com.acme.exception.SimulationProductTrueCoveragesException;
import com.acme.exception.error.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ProductNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse handleProductNotFoundException(ProductNotFoundException ex) {
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}
	
	
	@ExceptionHandler(ProductAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ErrorResponse handleProductAlreadyExistsException(ProductNotFoundException ex) {
		return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
	}
	
	
	@ExceptionHandler(CoverageNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse handleCoverageNotFoundException(CoverageNotFoundException ex) {
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}
	
	
	@ExceptionHandler(CoverageAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ErrorResponse handleCoverageAlreadyExistsException(ProductNotFoundException ex) {
		return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
	}
	
	
	@ExceptionHandler(ProspectNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse handleProspectNotFoundException(ProspectNotFoundException ex) {
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}
	
	
	@ExceptionHandler(ProspectAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ErrorResponse handlePropestAlreadyExistsException(ProductNotFoundException ex) {
		return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
	}
	
	
	@ExceptionHandler(SimulationNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse handleSimulationNotFoundException(SimulationNotFoundException ex) {
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}
	
	
	@ExceptionHandler(SimulationProductCoveragesException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse handleSimulationProductCoveragesException(SimulationProductCoveragesException ex) {
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}
	
	
	@ExceptionHandler(SimulationProductTrueCoveragesException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse handleSimulationProductTrueCoveragesException(SimulationProductTrueCoveragesException ex) {
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}
}
