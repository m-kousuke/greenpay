package com.greenpay.web;


import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	@ModelAttribute
	SelesVolumeForm selesVolumeForm(){
		return new SelesVolumeForm();
	}
	
	@RequestMapping(value="store/top",method=RequestMethod.GET)
	String StoreTop(Principal principal,Model model){
		model.addAttribute("store", storeService.AuthenticatedStore(principal.getName()));
		return "store/top";
	}
	
	@RequestMapping(value="store/salesvolumeForm",method=RequestMethod.GET)
	String SalesVolumeForm(Principal principal,Model model){
		Store store = storeService.AuthenticatedStore(principal.getName());
		List<SalesVolume> selesVolumes = purchaseHistoryService.GetPurchaseHistory(store.getId());
		model.addAttribute("store",store);
		model.addAttribute("salesVolumes",selesVolumes);
		return "store/salesvolumeForm";
	}
	
	@RequestMapping(value="store/salesvolumeForm",method=RequestMethod.POST)
	String SalesVolumeForm(@Validated SelesVolumeForm selesVolumeForm,BindingResult result,Principal principal,Model model){
		if(result.hasErrors()) {
			return "redirect:/store/salesvolumeForm?error";
		}else if(selesVolumeForm.getEndDate().isBefore(selesVolumeForm.getStartDate())){
			return "redirect:/store/salesvolumeForm?error";
		}
		Store store = storeService.AuthenticatedStore(principal.getName());
		List<SalesVolume> selesVolumes = purchaseHistoryService.GetPurchaseHistory(selesVolumeForm.getStartDate(),selesVolumeForm.getEndDate(),store.getId());
		model.addAttribute("store",store);
		model.addAttribute("salesVolumes",selesVolumes);
		return "store/salesvolumeForm";
	}
}
