package com.project;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StocksInFundController {

	@Autowired
	StocksInFundService sifs;
	
	
	@RequestMapping(value="/stocksinfund", method=RequestMethod.GET)
	public ArrayList<StocksInFund> getAllStocksInFund(){
		
		return sifs.getAllStocksInFund();
	}
	
	@RequestMapping(value="/stocksinfund/add", method=RequestMethod.POST)
	public String addStocksWeight(@RequestBody StocksInFund newStockWeights ){
	
		return sifs.addStocksWeight(newStockWeights); 
	}
	
}
