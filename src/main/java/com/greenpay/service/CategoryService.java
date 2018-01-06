package com.greenpay.service;

import java.time.LocalDateTime;
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

	LocalDateTime dateTime = LocalDateTime.now();

	public void create(Category category) {
		category.setCreatedAt(dateTime);
		category.setUpdatedAt(dateTime);
		categoryRepository.save(category);
	}

	public boolean isEmpty(Category category) {
		Category rs = categoryRepository.findByName(category.getName());
		if (rs == null) {
			return true;
		}
		return false;
	}

	public Category findOne(Integer id) {
		return categoryRepository.findOne(id);
	}

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public void update(Category category) {
		category.setUpdatedAt(dateTime);
		categoryRepository.save(category);
	}

	public void delete(Integer id) {
		categoryRepository.delete(id);
	}
}
