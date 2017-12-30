package com.greenpay.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="purchase_history_details")
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseHistoryDetail {
	@Id
	@GeneratedValue
	@Column(name="id",nullable=false)
	private int id;

	@Column(name="purchase_id",nullable=false)
	private int purchaseId;

	@Column(name="product_id",nullable=false)
	private int productId;

	@Column(name="quantity",nullable=false)
	private int quantity;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="purchase_id",insertable=false, updatable=false)
	private PurchaseHistory purchaseHistory;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="product_id",insertable=false, updatable=false)
	private Product product;
}