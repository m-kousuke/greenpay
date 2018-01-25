package com.greenpay.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenpay.domain.Calculate;
import com.greenpay.domain.Money;
import com.greenpay.domain.Product;
import com.greenpay.domain.PurchaseHistory;
import com.greenpay.domain.PurchaseHistoryDetail;
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
		product=productrepository.findByIdAndStoreIdAndActivated(id,storeId,2);
		if(product==null ) {
			return calculateList;
		}
		Calculate calculate = new Calculate();
		calculate.setProductId(id);
		calculate.setName(product.getName());
		BigDecimal discount = new BigDecimal("0.97");
		calculate.setPrice(product.getPrice().multiply(discount).setScale(0, BigDecimal.ROUND_HALF_UP));
		calculate.setQuantity(quantity);
		calculate.setSubtotal(calculate.getPrice().multiply(BigDecimal.valueOf(quantity)));
		calculateList.add(calculate);
		return calculateList;

	}

	public BigDecimal updateOfCredit(Money money, BigDecimal total) {
		BigDecimal balance =money.getCredit().subtract(total);//残高＝残高ーお買い上げ金額
		LocalDateTime dateTime = LocalDateTime.now();
		money.setCredit(balance);
		money.setUpdatedAt(dateTime);
		moneyrepository.save(money);
		return balance; 
	}

	public void registPurchaseHistory(Money money, String storeId, BigDecimal total, List<Calculate> calculateList) {
		LocalDateTime dateTime = LocalDateTime.now();
		PurchaseHistory purchaseHistory = new PurchaseHistory();
		purchaseHistory.setMoneyId(money.getId());
		purchaseHistory.setStoreId(storeId);
		purchaseHistory.setAmount(total);
		purchaseHistory.setCreatedAt(dateTime);
		purchaseHistoryrepository.save(purchaseHistory);//購入履歴を登録
		purchaseHistory= purchaseHistoryrepository.findTopByMoneyId(money.getId());//購入履歴IDを取得(※改善したい箇所)
		for (Calculate calculate : calculateList) {//購入商品情報をリストから１つ１つ取り出す
			PurchaseHistoryDetail purchaseHistoryDetail = new PurchaseHistoryDetail();
			purchaseHistoryDetail.setPurchaseId(purchaseHistory.getId());
			purchaseHistoryDetail.setProductId(calculate.getProductId());
			purchaseHistoryDetail.setQuantity(calculate.getQuantity());
			purchaseHistoryDetailrepository.save(purchaseHistoryDetail);//購入履歴詳細に登録
		}
		
	}

}
