package com.greenpay.web;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserFinishForm {
	@NotNull
	private String userId;
	@NotNull
	private String id;
	@NotNull
	private String password;
}
