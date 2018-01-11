package com.greenpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenpay.domain.Product;
import java.lang.String;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	Product findByNameAndStoreId(String name, String storeId);
	List<Product> findByNameContainingAndStoreId(String word,String storeId);
}
