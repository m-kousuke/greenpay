package com.greenpay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenpay.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	Product findByNameAndStoreId(String name, String storeId);
	List<Product> findByStoreIdAndActivatedNot(String storeId, int activated);
	List<Product> findByStoreIdNotAndActivatedNot(String storeId, int activated);
	List<Product> findByNameContainingAndStoreIdAndActivatedNot(String word,String storeId,int activated);
}
