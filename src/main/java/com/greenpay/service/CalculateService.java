package com.greenpay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenpay.domain.Calculate;
import com.greenpay.domain.Product;
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

	public List<Calculate> findByIdAndStoreId(String productId, Integer quantity, String storeId, List<Calculate> calculateList) {
		Product product = new Product();
		int id = Integer.parseInt(productId);
		product=productrepository.findByIdAndStoreId(id,storeId);
		if(product==null) {
			return calculateList;
		}
		Calculate calculate = new Calculate();
		calculate.setProductId(id);
		calculate.setName(product.getName());
		calculate.setPrice(product.getPrice());
		calculate.setQuantity(quantity);
		calculateList.add(calculate);
		return calculateList;
	}

}
