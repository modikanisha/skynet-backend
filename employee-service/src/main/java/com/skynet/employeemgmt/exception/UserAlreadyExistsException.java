package com.skynet.employeemgmt.exception;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExistsException extends DuplicateKeyException {
    private String email;
//    commented because the field is never used
//    private String userRole;


    public UserAlreadyExistsException(String email, String userRole, Throwable t) {
        super(String.format("User with email %s already exists", email), t);
        this.email = email;
//        this.userRole = userRole;
    }

    public String getEmail() {
        return email;
    }

}
