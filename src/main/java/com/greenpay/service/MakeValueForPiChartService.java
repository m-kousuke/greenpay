package com.greenpay.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.greenpay.domain.SalesVolume;
import com.greenpay.domain.ValueForPiChart;

@Service
public class MakeValueForPiChartService {

		public List<ValueForPiChart> ValueForPiChart(List<SalesVolume> salesVolumes){
			List<ValueForPiChart> valueForPiCharts = new ArrayList<ValueForPiChart>();
			List<String> productList = new ArrayList<String>();
			List<String> coloer = new ArrayList<String>();
			coloer.add("#FF0000");
			coloer.add("#B22222");
			coloer.add("#8B0000");
			coloer.add("#FFC0CB");
			coloer.add("#FF69B4");
			coloer.add("#FF1493");
			coloer.add("#C71585");
			coloer.add("#DB7093");
			if(salesVolumes.size()>0){
			ValueForPiChart valueForPiChart = new ValueForPiChart();
			valueForPiChart.setLabel(salesVolumes.get(0).getProduct().getName());
			valueForPiChart.setValue(salesVolumes.get(0).getProduct().getPrice().intValue()*salesVolumes.get(0).getPurchaseHistoryDetail().getQuantity());
			valueForPiChart.setColoer(coloer.get(0));
			valueForPiCharts.add(valueForPiChart);
			productList.add(valueForPiChart.getLabel());
			for(int i=1; i<salesVolumes.size();i++){
				if(!(productList.contains(salesVolumes.get(i).getProduct().getName()))){
					ValueForPiChart valueForPiChart2 = new ValueForPiChart();
					valueForPiChart2.setLabel(salesVolumes.get(i).getProduct().getName());
					valueForPiChart2.setValue(salesVolumes.get(i).getProduct().getPrice().intValue());
					valueForPiChart2.setColoer(coloer.get(i%8));
					valueForPiCharts.add(valueForPiChart2);
					productList.add(salesVolumes.get(i).getProduct().getName());
				}else{
					int index = productList.indexOf(salesVolumes.get(i).getProduct().getName());
					ValueForPiChart valueForPiChart2 = new ValueForPiChart();
					valueForPiChart2.setLabel(salesVolumes.get(i).getProduct().getName());
					valueForPiChart2.setValue(valueForPiCharts.get(index).getValue()+salesVolumes.get(i).getProduct().getPrice().intValue()*salesVolumes.get(i).getPurchaseHistoryDetail().getQuantity());
					valueForPiChart2.setColoer(coloer.get(i%8));
					valueForPiCharts.set(index, valueForPiChart2);
				}
			}
			}
			return valueForPiCharts;
		}
		
		public List<ValueForPiChart> ValueForPiChartByCategory(List<SalesVolume> salesVolumes){
			List<ValueForPiChart> valueForPiCharts = new ArrayList<ValueForPiChart>();
			List<String> categoryList = new ArrayList<String>();
			List<String> coloer = new ArrayList<String>();
			coloer.add("#00CED1");
			coloer.add("#5F9EA0");
			coloer.add("#4682B4");
			coloer.add("#B0C4DE");
			coloer.add("#87CEEB");
			coloer.add("#00BFFF");
			coloer.add("#7B68EE");
			coloer.add("#0000FF");
			if(salesVolumes.size()>0){
			ValueForPiChart valueForPiChart = new ValueForPiChart();
			valueForPiChart.setLabel(salesVolumes.get(0).getCategory().getName());
			valueForPiChart.setValue(salesVolumes.get(0).getProduct().getPrice().intValue()*salesVolumes.get(0).getPurchaseHistoryDetail().getQuantity());
			valueForPiChart.setColoer(coloer.get(0));
			valueForPiCharts.add(valueForPiChart);
			categoryList.add(valueForPiChart.getLabel());
			for(int i=1; i<salesVolumes.size();i++){
				if(!(categoryList.contains(salesVolumes.get(i).getCategory().getName()))){
					ValueForPiChart valueForPiChart2 = new ValueForPiChart();
					valueForPiChart2.setLabel(salesVolumes.get(i).getCategory().getName());
					valueForPiChart2.setValue(salesVolumes.get(i).getProduct().getPrice().intValue());
					valueForPiChart2.setColoer(coloer.get(i%8));
					valueForPiCharts.add(valueForPiChart2);
					categoryList.add(salesVolumes.get(i).getProduct().getName());
				}else{
					int index = categoryList.indexOf(salesVolumes.get(i).getCategory().getName());
					ValueForPiChart valueForPiChart2 = new ValueForPiChart();
					valueForPiChart2.setLabel(salesVolumes.get(i).getCategory().getName());
					valueForPiChart2.setValue(valueForPiCharts.get(index).getValue()+salesVolumes.get(i).getProduct().getPrice().intValue()*salesVolumes.get(i).getPurchaseHistoryDetail().getQuantity());
					valueForPiChart2.setColoer(coloer.get(i%8));
					valueForPiCharts.set(index, valueForPiChart2);
				}
			}
			}
			return valueForPiCharts;
		}
}
