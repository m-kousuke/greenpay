package com.greenpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenpay.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
