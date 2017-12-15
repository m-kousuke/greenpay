package com.greenpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenpay.domain.PurchaseHistory;

public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Integer> {

}
