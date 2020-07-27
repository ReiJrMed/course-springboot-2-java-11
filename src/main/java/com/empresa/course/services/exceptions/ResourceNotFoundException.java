package com.empresa.course.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{
	
	private final static long serialVersionUID = 1L;
	
	public ResourceNotFoundException(Object id) {
		super("Resource not found. Id " + id);
	}
}
