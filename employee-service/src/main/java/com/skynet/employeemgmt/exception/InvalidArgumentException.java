package com.skynet.employeemgmt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidArgumentException extends RuntimeException {

	private static final long serialVersionUID = 3025545574720439968L;

	public InvalidArgumentException(String message) {
		super(message);
	}
}