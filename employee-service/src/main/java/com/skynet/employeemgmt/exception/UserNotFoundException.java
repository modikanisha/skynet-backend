package com.skynet.employeemgmt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends UsernameNotFoundException {
    private static final long serialVersionUID = 5635916914916365141L;
    private String email;

    public UserNotFoundException(String email) {
        super("INCORRECT_EMAIL_ADDRESS_OR_PASS_MSG");
        this.email = email;
    }
    public UserNotFoundException(String email, String message) {
        super(message);
        this.email = email;
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getEmail() {
        return email;
    }
}