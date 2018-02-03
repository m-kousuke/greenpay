package com.greenpay.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesVolume {

	private PurchaseHistory purchaseHistory;
	
	private PurchaseHistoryDetail purchaseHistoryDetail;
	
	private Product product;
	
	private Category category;
}
