package com.skynet.employeemgmt.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Login {
	@Email(message = "Invalid email address")
	@NotEmpty
	private String email;
	@NotNull
	private String password;
	private boolean rememberMe;
	private boolean encoded;
}
