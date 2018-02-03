package com.greenpay.web;


import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

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
import com.greenpay.domain.ValueForPiChart;
import com.greenpay.service.MakeValueForPiChartService;
import com.greenpay.service.PurchaseHistoryService;
import com.greenpay.service.StoreService;

@Controller
public class StoreController {
	@Autowired
	StoreService storeService;
	@Autowired
	PurchaseHistoryService purchaseHistoryService;
	@Autowired
	MakeValueForPiChartService makeValueForPiChartService;
	@Autowired
	HttpSession session;
	@ModelAttribute
	SelesVolumeForm selesVolumeForm(){
		return new SelesVolumeForm();
	}
	
	@RequestMapping(value="store/top",method=RequestMethod.GET)
	String StoreTop(Principal principal,Model model){
		model.addAttribute("store", storeService.AuthenticatedStore(principal.getName()));
		session.removeAttribute("money");
		session.removeAttribute("number");
		session.removeAttribute("calculateList");
		session.removeAttribute("total");
		return "store/top";
	}
	
	@RequestMapping(value="store/salesvolumeForm",method=RequestMethod.GET)
	String SalesVolumeForm(Principal principal,Model model){
		Store store = storeService.AuthenticatedStore(principal.getName());
		List<SalesVolume> salesVolumes = purchaseHistoryService.GetPurchaseHistory(store.getId());
		if(salesVolumes!=null){
		List<ValueForPiChart> salesVolumeForPiCharts = makeValueForPiChartService.ValueForPiChart(salesVolumes);
		List<ValueForPiChart> valueForPiCharts = makeValueForPiChartService.ValueForPiChartByCategory(salesVolumes);
		model.addAttribute("chartValues",salesVolumeForPiCharts);
		model.addAttribute("chartValuesByCategory",valueForPiCharts);
		}
		model.addAttribute("store",store);
		model.addAttribute("salesVolumes",salesVolumes);
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
		List<SalesVolume> salesVolumes = purchaseHistoryService.GetPurchaseHistory(selesVolumeForm.getStartDate(),selesVolumeForm.getEndDate(),store.getId());
		if(salesVolumes!=null){
		List<ValueForPiChart> salesVolumeForPiCharts = makeValueForPiChartService.ValueForPiChart(salesVolumes);
		List<ValueForPiChart> valueForPiCharts = makeValueForPiChartService.ValueForPiChartByCategory(salesVolumes);
		model.addAttribute("chartValues",salesVolumeForPiCharts);
		model.addAttribute("chartValuesByCategory",valueForPiCharts);
		}
		model.addAttribute("store",store);
		model.addAttribute("salesVolumes",salesVolumes);
		return "store/salesvolumeForm";
	}
}
