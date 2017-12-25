package com.greenpay.web;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
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
	@DecimalMin("1")
	@DecimalMax("20000")
	private BigDecimal price;
}
