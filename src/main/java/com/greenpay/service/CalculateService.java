package com.greenpay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenpay.repository.MoneyRepository;
import com.greenpay.repository.ProductRepository;
import com.greenpay.repository.PurchaseHistoryDetailRepository;
import com.greenpay.repository.PurchaseHistoryRepository;
import com.greenpay.repository.UserRepository;

@Service
public class CalculateService {
	@Autowired
	UserRepository userreposirtory;
	@Autowired
	ProductRepository productrepository;
	@Autowired
	MoneyRepository moneyrepository;
	@Autowired
	PurchaseHistoryRepository purchaseHistoryrepository;
	@Autowired
	PurchaseHistoryDetailRepository purchaseHistoryDetailrepository;

}
