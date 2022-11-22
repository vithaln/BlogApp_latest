package com.vitu.code.exceptions;

public class ResourceNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ResourceNotFoundException() {
		super("NOT FOUND ON SERVER!...");
	}
	public ResourceNotFoundException(String message) {
		super(message);
	}
}
