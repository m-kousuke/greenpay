package com.greenpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenpay.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
