package com.greenpay.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
	@Column(name="id")
	private int id;
	@Column(name="purchase_id",nullable=false)
	private int purchase_id;
	@Column(name="product_id",nullable=false)
	private int product_id;
	@Column(name="quanity",nullable=false)
	private int quanity;
}
