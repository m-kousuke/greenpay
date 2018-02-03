package com.greenpay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenpay.domain.PurchaseHistoryDetail;

public interface PurchaseHistoryDetailRepository extends JpaRepository<PurchaseHistoryDetail, Integer>{
    // select p from PurchaseHistoryDeatail p where p.purchaseId = ?1
    List<PurchaseHistoryDetail> findByPurchaseId(int purchaseId);
}
