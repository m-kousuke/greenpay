package com.greenpay.web;


import java.security.Principal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	String SalesVolumeForm(@RequestParam(name="month",required=false) Month month,Principal principal,Model model){
		if(month==null){
			month=LocalDateTime.now().getMonth();
			System.out.println(LocalDateTime.now().getMonth());
		}
		Store store = storeService.AuthenticatedStore(principal.getName());
		List<SalesVolume> selesVolumes = purchaseHistoryService.GetPurchaseHistory(store.getId());
		model.addAttribute("store",store);
		model.addAttribute("salesVolumes",selesVolumes);
		return "store/salesvolumeForm";
	}
}
