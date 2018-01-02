package com.greenpay.web;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ProductForm {
	@NotNull(message="必須項目です")
	@Size(min = 1, max = 64, message="1~64文字で入力してください")
	private String name;

	@NotNull(message="必須項目です")
	private Integer categoryId;

	@NotNull(message="必須項目です")
	@DecimalMin(value="1",message="1円以上で入力してください")
	@DecimalMax(value="20000", message="2万円以下で入力してください")
	private BigDecimal price;

	@NotNull(message="必須項目です")
	@Min(value = 0, message="不正な値です")
	@Max(value = 2, message="不正な値です")
	private Integer activated;
}
