package com.greenpay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenpay.repository.ProductRepository;

@Service
@Transactional
public class ProductService {
	@Autowired
	ProductRepository productRepository;

	// public void create(Product product) {
	// 	String date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS").format(LocalDateTime.now());
	// 	product.setCreatedAt(date);
	// 	product.setUpdatedAt(date);
	// 	productRepository.save(product);
	// }

	// public Product findOne(Integer id) {
	// 	return productRepository.findOne(id);
	// }

	// public List<Product> findAll() {
	// 	return productRepository.findAll();
	// }

	// public void update(Product product) {
	// 	String date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS").format(LocalDateTime.now());
	// 	product.setUpdatedAt(date);
	// 	productRepository.save(product);
	// }

	// public void delete(Integer id) {
	// 	productRepository.delete(id);
	// }
}