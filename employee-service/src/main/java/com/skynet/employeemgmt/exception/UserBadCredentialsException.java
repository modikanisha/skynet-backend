package com.skynet.employeemgmt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Incorrect email address or password")
public class UserBadCredentialsException extends BadCredentialsException {
    private static final long serialVersionUID = 5069882135071031587L;
    private String email;

    public UserBadCredentialsException(String email) {
        super("INCORRECT_EMAIL_ADDRESS_OR_PASS_MSG");
        this.email = email;
    }

    public UserBadCredentialsException(String email, String message) {
        super(message);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}