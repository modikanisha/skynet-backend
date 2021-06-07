package com.skynet.employeemgmt.exception;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CommonValidationException extends ValidationException {

    private String email = "unknown";
    private List<String> violations = new ArrayList<>();

    public CommonValidationException(String email, String violation) {
        this(email, Collections.singletonList(violation));
    }

    public CommonValidationException(String email, String violation, Throwable cause) {
        this(email, Collections.singletonList(violation), cause);
    }

    public CommonValidationException(String email, List<String> violations, Throwable cause) {
        super(cause);
        this.email = email;
        this.violations = violations;
    }

    public CommonValidationException(String email, List<String> violations) {
        super();
        this.email = email;
        this.violations = violations;
    }

    public CommonValidationException(String email, Collection<ConstraintViolation<Object>> violations) {
        this(email,
                violations.stream().map(o -> o.getPropertyPath() + " " + o.getMessage()).collect(Collectors.toList()));
    }


    public String getEmail() {
        return email;
    }

    @Override
    public String getMessage() {
        return violations.stream().collect(Collectors.joining(", "));
    }
}