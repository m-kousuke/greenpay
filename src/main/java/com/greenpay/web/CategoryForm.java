package com.greenpay.web;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CategoryForm {
	@NotNull
	@Size(min = 1, max = 64)
	private String name;
}