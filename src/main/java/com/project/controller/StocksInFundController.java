package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.StocksInFund;
import com.project.service.StocksInFundService;

@RestController
public class StocksInFundController {

	@Autowired
	StocksInFundService sifs;

	@RequestMapping(value = "/stocksinfund", method = RequestMethod.GET)
	public List<StocksInFund> getAllStocksInFund() {
		return sifs.getAllStocksInFund();
	}

	@RequestMapping(value = "/stocksinfund/add", method = RequestMethod.POST)
	public String addStocksWeight(@RequestBody StocksInFund newStockWeights) {

		return sifs.addStocksWeight(newStockWeights);
	}

}
