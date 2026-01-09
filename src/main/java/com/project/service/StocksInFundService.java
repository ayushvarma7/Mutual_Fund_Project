package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.StocksInFund;
import com.project.repository.StocksInFundRepository;

@Service
public class StocksInFundService {

	@Autowired
	StocksInFundRepository sifRepository;

	public List<StocksInFund> getAllStocksInFund() {
		return (List<StocksInFund>) sifRepository.findAll();
	}

	public String addStocksWeight(StocksInFund newStockWeights) {

		sifRepository.save(newStockWeights);
		return "Successfully updated stock weights";
	}

}
