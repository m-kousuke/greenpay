package com.greenpay.web;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CategoryForm {
	@NotNull(message = "必須項目です")
	@Size(min = 1, max = 64, message ="1~64文字で入力してください")
    @Pattern(regexp="[^a-zA-Z1-9 |　]+$",message="英数字,空白文字は使用できませんん")
	private String name;
}