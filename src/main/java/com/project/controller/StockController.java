package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.Stock;
import com.project.service.StockService;

@RestController
public class StockController {

	@Autowired
	StockService ss;

	@RequestMapping(value = "/stocks", method = RequestMethod.GET)
	public List<Stock> getAllStocks() {
		return ss.getAllStocks();
	}

	@RequestMapping(value = "/stocks/byid", method = RequestMethod.GET)
	public List<Stock> getAllStocksBy1() {
		return ss.getAllStocksBy1();
	}

	@RequestMapping(value = "/stocks/add", method = RequestMethod.POST)
	public String addStocks(@RequestBody Stock newStock) {
		return ss.addStocks(newStock);
	}

	@RequestMapping(value = "stocks/id/{stockId}", method = RequestMethod.GET)
	public Stock getStockInfo(@PathVariable int stockId) {
		return ss.getStockInfo(stockId);
	}

}
