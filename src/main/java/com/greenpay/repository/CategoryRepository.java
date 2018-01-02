package com.greenpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.greenpay.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("select c from Category c where c.name = ?1")
    Category findCategory(String name);
}
