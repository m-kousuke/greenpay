package com.greenpay.web;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import lombok.Data;

@Data
public class UserForm {
	@NotNull
	private String lastName;
	@NotNull
	private String firstName;
	@NotNull
	@Pattern(regexp="^[\\u3040-\\u309F]+$",message="ひらがなで入力してください")
	private String lastNameKana;
	@NotNull
	@Pattern(regexp="^[\\u3040-\\u309F]+$",message="ひらがなで入力してください")
	private String firstNameKana;
	@NotNull
	@Email(message="メールアドレスを入力してください")
	private String email;
	@NotNull
	@Size(min=8,max=16)
	@Pattern(regexp="^([a-zA-Z0-9]{8,16})$",message="パスワードは半角英数字8~16文字で入力してください")
	private String password;
}
