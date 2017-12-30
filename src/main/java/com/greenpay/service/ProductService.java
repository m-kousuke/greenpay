package com.greenpay.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenpay.domain.Product;
import com.greenpay.domain.Store;
import com.greenpay.repository.ProductRepository;

@Service
@Transactional
public class ProductService {
	@Autowired
	ProductRepository productRepository;

	LocalDateTime dateTime = LocalDateTime.now();

	public void create(Product product, Store store) {
		product.setStoreId(store.getId());
		product.setCreatedAt(dateTime);
		product.setUpdatedAt(dateTime);
		productRepository.save(product);
	}

	public Product findOne(Integer id) {
		return productRepository.findOne(id);
	}

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public void update(Product product) {
		product.setUpdatedAt(dateTime);
		productRepository.save(product);
	}

	public void delete(Integer id) {
		productRepository.delete(id);
	}
}