package com.greenpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenpay.domain.PurchaseHistoryDetail;

public interface PurchaseHistoryDetailRepository extends JpaRepository<PurchaseHistoryDetail, Integer>{

}
