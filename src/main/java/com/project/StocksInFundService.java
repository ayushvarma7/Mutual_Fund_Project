package com.project;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StocksInFundService {

	@Autowired
	StocksInFundRepsoitory sifRepository;


	public ArrayList<StocksInFund> getAllStocksInFund(){

		return (ArrayList<StocksInFund>) sifRepository.findAll();
	}
	
	public String addStocksWeight(StocksInFund newStockWeights) {
		
		sifRepository.save(newStockWeights);
		return "Successfully updated stock weights";
	}

	


}
