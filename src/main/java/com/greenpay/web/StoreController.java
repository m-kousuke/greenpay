package com.greenpay.web;


import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.greenpay.domain.SalesVolume;
import com.greenpay.domain.Store;
import com.greenpay.service.PurchaseHistoryService;
import com.greenpay.service.StoreService;

@Controller
public class StoreController {
	@Autowired
	StoreService storeService;
	@Autowired
	PurchaseHistoryService purchaseHistoryService;
	
	@RequestMapping(value="store/top",method=RequestMethod.GET)
	String StoreTop(){
		return "store/top";
	}
	
	@RequestMapping(value="store/salesvolumeForm",method=RequestMethod.GET)
	String SalesVolumeForm(Principal principal,Model model){
		Store store = storeService.AuthenticatedStore(principal.getName());
		List<SalesVolume> selesVolumes = purchaseHistoryService.GetPurchaseHistory(store.getId());
		model.addAttribute(store);
		model.addAllAttributes(selesVolumes);
		return "store/salesvolumeForm";
	}
}
