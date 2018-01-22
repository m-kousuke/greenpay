package com.greenpay.web;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CategoryForm {
	@NotNull(message = "必須項目です")
	@Size(min = 1, max = 64, message ="1~64文字で入力してください")
    @Pattern(regexp="^[\\u3040-\\u30FF]+$",message="商品名は全角文字で入力してください")
	private String name;
}