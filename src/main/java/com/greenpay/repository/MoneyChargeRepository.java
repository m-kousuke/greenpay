package com.greenpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenpay.domain.MoneyCharge;

public interface MoneyChargeRepository extends JpaRepository<MoneyCharge, Integer>{

}