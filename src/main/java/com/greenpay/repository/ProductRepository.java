package com.greenpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.greenpay.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("select p from Product p where p.name = ?1 and p.storeId= ?2")
    Product findProduct(String name, String storeId);
}
