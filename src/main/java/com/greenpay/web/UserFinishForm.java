package com.greenpay.web;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserFinishForm {
	@NotNull
	private String userId;
	@NotNull
	@Size(min=9, max=9)
	@Pattern(regexp="^([a-zA-Z0-9]{9})$",message="図書館利用番号は半角英数字9文字で入力してください")
	private String id;
	@NotNull
	@Size(min=8,max=16)
	@Pattern(regexp="^([a-zA-Z0-9]{8,16})$",message="パスワードは半角英数字8~16文字で入力してください")
	private String password;
}
