package com.greenpay.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenpay.domain.Category;
import com.greenpay.repository.CategoryRepository;

@Service
@Transactional
public class CategoryService {
	@Autowired
	CategoryRepository categoryRepository;

	public void create(Category category) {
		String date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS").format(LocalDateTime.now());
		category.setCreatedAt(date);
		category.setUpdatedAt(date);
		categoryRepository.save(category);
	}

	public Category findOne(Integer id) {
		return categoryRepository.findOne(id);
	}

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public void update(Category category) {
		String date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS").format(LocalDateTime.now());
		category.setUpdatedAt(date);
		categoryRepository.save(category);
	}

	// public void delete(Integer id) {
	// 	categoryRepository.delete(id);
	// }
}
