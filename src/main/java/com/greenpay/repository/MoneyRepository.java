package com.greenpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenpay.domain.Money;

public interface MoneyRepository extends JpaRepository<Money, String>{



}