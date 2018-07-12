package com.test.exceptions;

public class UserNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1077397310246435708L;
	
	
	public UserNotFoundException(String message) {
		super(message);
	}
	
	
}
