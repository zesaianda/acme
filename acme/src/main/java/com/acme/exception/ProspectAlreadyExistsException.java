package com.acme.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.CONFLICT)
public class ProspectAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 613630774133098380L;
	
	private String message;

	public ProspectAlreadyExistsException(String message) {
		super();
		this.message = message;
	}
}
