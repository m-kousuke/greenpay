package com.greenpay.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

	public void create(Product product, Store store) {
		String date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS").format(LocalDateTime.now());
		product.setStoreId(store.getId());
		product.setCreatedAt(date);
		product.setUpdatedAt(date);
		productRepository.save(product);
	}

	public Product findOne(Integer id) {
		return productRepository.findOne(id);
	}

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public void update(Product product) {
		String date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS").format(LocalDateTime.now());
		product.setUpdatedAt(date);
		productRepository.save(product);
	}

	public void delete(Integer id) {
		productRepository.delete(id);
	}
}