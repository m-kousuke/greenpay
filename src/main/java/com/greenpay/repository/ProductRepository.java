package com.greenpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenpay.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    // select p from Product p where p.name = ?1 and p.storeId= ?2
    Product findByNameAndStoreId(String name, String storeId);
}
