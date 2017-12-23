package com.greenpay.web;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserForm {
	@NotNull
	private String lastName;
	@NotNull
	private String firstName;
	@NotNull
	private String lastNameKana;
	@NotNull
	private String firstNameKana;
	@NotNull
	private String email;
	@NotNull
	private String password;
}
