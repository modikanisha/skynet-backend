package com.skynet.employeemgmt.exception;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class UserLoginException extends CommonValidationException {
	public UserLoginException(String email, Set<ConstraintViolation<Object>> validationResult) {
		super(email, validationResult);
	}

	public UserLoginException(String email, String message, Throwable cause) {
		super(email, message, cause);
	}
}