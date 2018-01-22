package com.greenpay.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValueForPiChart {
	private int value;
	
	private String coloer;
	
	private String label;
}
