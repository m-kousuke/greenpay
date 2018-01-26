package com.greenpay.web;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class UserEditForm {

	@NotNull
	@Pattern(regexp="^([a-zA-Z0-9]{8,16})$",message="半角英数字8~16文字で入力してください")
	private String password;

	@NotNull
	@Pattern(regexp="^([a-zA-Z0-9]{8,16})$",message="半角英数字8~16文字で入力してください")
	private String newPassword;

	@NotNull
	@Pattern(regexp="^([a-zA-Z0-9]{8,16})$",message="半角英数字8~16文字で入力してください")
	private String againNewPassword;
}
