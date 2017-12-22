package com.greenpay.web;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserForm {
	@NotNull
	@Size(min = 1, max = 127)
	private String email;
	@NotNull
	@Size(min = 1, max = 127)
	private String password;
}
