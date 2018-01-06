package com.greenpay.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenpay.domain.Money;
import com.greenpay.domain.PurchaseHistory;
import com.greenpay.domain.PurchaseHistoryDetail;
import com.greenpay.domain.SalesVolume;
import com.greenpay.domain.User;
import com.greenpay.repository.PurchaseHistoryRepository;

@Service
public class PurchaseHistoryService {

	@Autowired
	PurchaseHistoryRepository purchaseHistoryRepository;

	public List<SalesVolume> GetPurchaseHistory(String storeId){
		List<PurchaseHistory> purchaseHistory = purchaseHistoryRepository.findByStoreId(storeId);
		List<SalesVolume> salesVolumes = new ArrayList();
		for (int i = 0; i < purchaseHistory.size(); i++) {
			PurchaseHistory purchaseHistoryNode = purchaseHistory.get(i);
			List<PurchaseHistoryDetail> purchaseHistoryDetails = purchaseHistoryNode.getPurchaseHistoryDetail();
			for (int j = 0; j < purchaseHistoryDetails.size(); j++) {
				PurchaseHistoryDetail purchaseHistoryDetail = purchaseHistoryDetails.get(j);
				SalesVolume salesVolume = new SalesVolume();
				salesVolume.setPurchaseHistory(purchaseHistoryNode);
				salesVolume.setPurchaseHistoryDetail(purchaseHistoryDetail);
				salesVolume.setProduct(purchaseHistoryDetail.getProduct());
				salesVolume.setCategory(purchaseHistoryDetail.getProduct().getCategory());
				salesVolumes.add(salesVolume);
			}
		}
		return salesVolumes;
	}

	public List<SalesVolume> GetPurchaseHistory(LocalDate startDate,LocalDate endDate,String storeId){
		List<PurchaseHistory> purchaseHistory = purchaseHistoryRepository.findByStoreIdAndCreatedAtBetween(storeId, LocalDateTime.of(startDate, LocalTime.MIN), LocalDateTime.of(endDate, LocalTime.MAX));
		List<SalesVolume> salesVolumes = new ArrayList();
		for (int i = 0; i < purchaseHistory.size(); i++) {
			PurchaseHistory purchaseHistoryNode = purchaseHistory.get(i);
			List<PurchaseHistoryDetail> purchaseHistoryDetails = purchaseHistoryNode.getPurchaseHistoryDetail();
			for (int j = 0; j < purchaseHistoryDetails.size(); j++) {
				PurchaseHistoryDetail purchaseHistoryDetail = purchaseHistoryDetails.get(j);
				SalesVolume salesVolume = new SalesVolume();
				salesVolume.setPurchaseHistory(purchaseHistoryNode);
				salesVolume.setPurchaseHistoryDetail(purchaseHistoryDetail);
				salesVolume.setProduct(purchaseHistoryDetail.getProduct());
				salesVolume.setCategory(purchaseHistoryDetail.getProduct().getCategory());
				salesVolumes.add(salesVolume);
			}
		}
		return salesVolumes;
	}

	public List<PurchaseHistory> findByMoneyId(User user) {
	    Money money = user.getMoney();
	    List<PurchaseHistory> rs = purchaseHistoryRepository.findByMoneyId(money.getId());
	    return rs;
	}
}
