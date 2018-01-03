package com.greenpay.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenpay.domain.PurchaseHistory;

public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Integer> {

	List<PurchaseHistory> findByStoreId(String storeid);
	
	List<PurchaseHistory> findByStoreIdAndCreatedAtBetween(String storeId,LocalDateTime startDate,LocalDateTime endDate);
}
