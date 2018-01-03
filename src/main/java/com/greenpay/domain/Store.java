package com.greenpay.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "stores")
@AllArgsConstructor
@NoArgsConstructor
public class Store {
	@Id
	@Column(name = "id", nullable = false)
	private String id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "activated", nullable = false)
	private int activated;

	@Column(name = "created_at", nullable = false)
	@Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
	private LocalDateTime createdAt;

	@Column(name = "updated_at", nullable = false)
	@Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
	private LocalDateTime updatedAt;

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY,mappedBy="store")
	private List<Product> Products;
}
