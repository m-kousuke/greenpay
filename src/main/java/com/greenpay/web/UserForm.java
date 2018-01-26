package com.greenpay.web;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import lombok.Data;

@Data
public class UserForm {
	@NotNull
	@Pattern(regexp="[^[0-9] |　]+$",message="数字、空白文字は使用できません")
	private String lastName;
	@NotNull
	@Pattern(regexp="[^[0-9] |　]+$",message="数字、空白文字は使用できません")
	private String firstName;
	@NotNull
	@Pattern(regexp="^[\\u3040-\\u309F]+$",message="ひらがなで入力してください。また、空白文字などは使用できません")
	private String lastNameKana;
	@NotNull
	@Pattern(regexp="^[\\u3040-\\u309F]+$",message="ひらがなで入力してください。また、空白文字などは使用できません")
	private String firstNameKana;
	@NotNull
	@Email(message="メールアドレスを入力してください")
	private String email;
	@NotNull
	@Size(min=8,max=16)
	@Pattern(regexp="^([a-zA-Z0-9]{8,16})$",message="パスワードは半角英数字8~16文字で入力してください")
	private String password;
}
