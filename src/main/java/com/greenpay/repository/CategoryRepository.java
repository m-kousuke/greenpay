package com.greenpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenpay.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // select c from Category c where c.name = ?1
    Category findByName(String name);
}
