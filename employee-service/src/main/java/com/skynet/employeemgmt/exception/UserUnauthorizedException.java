package com.skynet.employeemgmt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UserUnauthorizedException extends RuntimeException {
    public UserUnauthorizedException() {
        super();
    }

    public UserUnauthorizedException(Throwable cause) {
        super(cause);
    }
    public UserUnauthorizedException(String message) {
        super(message);
    }

}