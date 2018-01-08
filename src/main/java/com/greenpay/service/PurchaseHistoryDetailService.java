package com.greenpay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenpay.domain.PurchaseHistoryDetail;
import com.greenpay.repository.PurchaseHistoryDetailRepository;

@Service
@Transactional
public class PurchaseHistoryDetailService {
    @Autowired
    PurchaseHistoryDetailRepository purchaseHistoryDetailRepository;

    public List<PurchaseHistoryDetail> findByPurchaseId(Integer purchaseId) {
        return purchaseHistoryDetailRepository.findByPurchaseId(purchaseId);
    }
}
