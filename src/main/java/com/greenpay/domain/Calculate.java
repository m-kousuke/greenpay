package com.greenpay.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Calculate {
	private Integer productId;
	
	private String name;
	
	private Integer quantity;
	
	private BigDecimal price;
}
