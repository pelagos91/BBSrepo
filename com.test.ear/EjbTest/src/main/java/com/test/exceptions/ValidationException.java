package com.test.exceptions;

public class ValidationException extends Exception {
	
	private static final long serialVersionUID = 1077397310246435708L;
	
	private int errorCode;
	
	public ValidationException(String message, int code) {
		super(message);
        this.errorCode = code;
	}
	
	public ValidationException(String message, Throwable cause, int code) {
	        super(message, cause);
	        this.errorCode = code;
	    }
	
	public int getErrorCode() {
		return this.errorCode;
	}
	
}
