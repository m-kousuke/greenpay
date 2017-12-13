package com.greenpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenpay.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Integer> {

}
