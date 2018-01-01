package com.greenpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenpay.domain.PurchaseHistory;
import java.lang.String;
import java.util.List;
import java.time.LocalDateTime;

public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Integer> {

	List<PurchaseHistory> findByStoreId(String storeid);
	
	List<PurchaseHistory> findByCreatedAtBetween(LocalDateTime start,LocalDateTime end);
}
