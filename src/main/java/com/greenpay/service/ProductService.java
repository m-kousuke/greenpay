package com.greenpay.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenpay.domain.Product;
import com.greenpay.repository.ProductRepository;

@Service
@Transactional
public class ProductService {
	@Autowired
	ProductRepository productRepository;

	LocalDateTime dateTime = LocalDateTime.now();

	public void create(Product product) {
		product.setCreatedAt(dateTime);
		product.setUpdatedAt(dateTime);
		productRepository.save(product);
	}

	public Product isEmpty(Product product) {
		return productRepository.findByNameAndStoreId(product.getName(), product.getStoreId());

	}

	public Product findOne(Integer id) {
		return productRepository.findOne(id);
	}

	public List<Product> findByStoreIdAndActivatedNot(String storeId) {
		return productRepository.findByStoreIdAndActivatedNot(storeId, 0);
	}

	public List<Product> findByStoreIdNotAndActivatedNot(String storeId) {
	    return productRepository.findByStoreIdNotAndActivatedNot(storeId, 0);
	}

	public void update(Product product) {
		product.setUpdatedAt(dateTime);
		productRepository.save(product);
	}

	public List<Product> findByNameAndStoreId(String word,String storeId){
		return productRepository.findByNameContainingAndStoreIdAndActivatedNot(word, storeId,0);
	}
}