package com.greenpay.web;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class UserFinishForm {
	@NotNull
	private String userId;
	@NotNull
	@Pattern(regexp="^([a-zA-Z0-9]{9})$",message="半角英数字9文字で入力してください")
	private String id;
	@NotNull
	@Pattern(regexp="^([a-zA-Z0-9]{8,16})$",message="パスワードは半角英数字8~16文字で入力してください")
	private String password;
}
