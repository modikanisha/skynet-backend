package com.skynet.employeemgmt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import java.util.Set;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PasswordChangeException extends CommonValidationException {

    public PasswordChangeException(String email, Throwable cause) {
        super(email, String.format("User with email %s failed password change", email), cause);
    }

    public PasswordChangeException(String email, String violation) {
        super(email, violation);
    }

    public PasswordChangeException(String email, String violation, Throwable cause) {
        super(email, violation, cause);
    }
    public PasswordChangeException(String email, Set<ConstraintViolation<Object>> result) {
        super(email, result);
    }

}
