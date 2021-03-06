package com.greenpay.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenpay.domain.Product;
import com.greenpay.repository.ProductRepository;

@Service
@Transactional
public class ProductService {
	@Autowired
	ProductRepository productRepository;

	public void create(Product product) {
	    LocalDateTime dateTime = LocalDateTime.now();
		product.setCreatedAt(dateTime);
		product.setUpdatedAt(dateTime);
		productRepository.save(product);
	}

	public Product isEmpty(Product product) {
		return productRepository.findByNameAndStoreId(product.getName(), product.getStoreId());

	}

	public Product findOne(Integer id) {
		return productRepository.findOne(id);
	}

	public List<Product> findByStoreIdAndActivatedNot(String storeId) {
		return productRepository.findByStoreIdAndActivatedNot(storeId, 0);
	}

	public List<Product> findByStoreIdNotAndActivatedNot(String storeId) {
	    return productRepository.findByStoreIdNotAndActivatedNot(storeId, 0);
	}
	

	public void update(Product product) {
		product.setUpdatedAt(LocalDateTime.now());
		productRepository.save(product);
	}

	public List<Product> findByNameAndStoreId(String word,String storeId){
		return productRepository.findByNameContainingAndStoreIdAndActivatedNot(word, storeId,0);
	}

	public void delete(Integer id) {
	    Product rs = this.findOne(id);
	    rs.setActivated(0);
	    rs.setUpdatedAt(LocalDateTime.now());
	    productRepository.save(rs);
	}
	
	public List<String> GetCategoryListForProductSerach(List<Product> products){
		List<String> categoryList = new ArrayList<String>();
		String categoryName = products.get(0).getCategory().getName();
		for(int i=0;i<products.size();i++){
			if(!categoryList.contains(products.get(i).getCategory().getName())){
				categoryList.add(products.get(i).getCategory().getName());
			}
		}
		return categoryList;
	}

	public List<Product> findByStoreIdAndActivatedYes(String storeId) {
		 return productRepository.findByStoreIdAndActivated(storeId, 2);
	}
}