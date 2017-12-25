package com.greenpay.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "store_id", nullable = false)
	private String createdStroreId;

	@Column(name = "category_id", nullable = false)
	private int categoryId;

	@Column(name = "price", nullable = false)
	private BigDecimal price;

	@Column(name = "activated", nullable = false)
	private int activated;

	@Column(name = "created_at", nullable = false)
	private String createdAt;

	@Column(name = "updated_at", nullable = false)
	private String updatedAt;
}
