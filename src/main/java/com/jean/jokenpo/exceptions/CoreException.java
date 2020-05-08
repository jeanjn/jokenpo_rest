package com.jean.jokenpo.exceptions;

import org.springframework.http.HttpStatus;

public class CoreException extends Exception {
	
	private static final long serialVersionUID = 2214233741947254397L;

	private HttpStatus statusCode;
	
	public CoreException(HttpStatus statusCode, String mensagem) {
		super(mensagem);
		this.statusCode = statusCode;
	}
	
	public CoreException(String mensagem) {
		super(mensagem);
		this.statusCode = HttpStatus.BAD_REQUEST;
	}
	
	public CoreException(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}
	
	public HttpStatus getStatusCode() {
		return this.statusCode;
	}

}
