package com.greenpay.web;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ProductForm {
	@NotNull
	@Size(min = 1, max = 64)
	private String name;

	@NotNull
	private Integer categoryId;

	@NotNull
	@Size(min = 1, max = 20000)
	private BigDecimal price;
}
