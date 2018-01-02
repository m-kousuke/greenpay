package com.greenpay.web;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CategoryForm {
	@NotNull(message = "必須項目です")
	@Size(min = 1, max = 64, message ="1~64文字で入力してください")
	private String name;
}