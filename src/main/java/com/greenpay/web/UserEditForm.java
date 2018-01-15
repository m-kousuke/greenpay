package com.greenpay.web;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserEditForm {

	@NotNull
	@Size(min=8,max=16)
	@Pattern(regexp="^([a-zA-Z0-9]{8,16})$")
	private String password;

	@NotNull
	@Size(min=8,max=16)
	@Pattern(regexp="^([a-zA-Z0-9]{8,16})$")
	private String newPassword;

	@NotNull
	@Size(min=8,max=16)
	@Pattern(regexp="^([a-zA-Z0-9]{8,16})$")
	private String againNewPassword;
}
