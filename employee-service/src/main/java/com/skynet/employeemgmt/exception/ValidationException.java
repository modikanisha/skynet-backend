package com.skynet.employeemgmt.exception;

public class ValidationException extends RuntimeException {
	public ValidationException(String message) {
		super(message);
	}

	public ValidationException() {
		super();
	}

	public ValidationException(Throwable cause) {
		super(cause);
	}
}