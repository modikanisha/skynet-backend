package com.skynet.employeemgmt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class ActivateUserPasswordException  extends ValidationException {
    public ActivateUserPasswordException(String exceptionString) {
        super(exceptionString);
    }
}
