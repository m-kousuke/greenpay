package com.greenpay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenpay.domain.Store;
import com.greenpay.repository.StoreRepository;

@Service
public class StoreService {

	@Autowired
	StoreRepository storeRepository;
	
	public Store AuthenticatedStore(String storeId){
		return storeRepository.findOne(storeId);
	}
}
