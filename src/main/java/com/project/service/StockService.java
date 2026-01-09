package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Stock;
import com.project.repository.StockRepository;

@Service
public class StockService {

	@Autowired
	StockRepository sRepository;

	public List<Stock> getAllStocks() {
		return (List<Stock>) sRepository.findAll();
	}

	public String addStocks(Stock newStock) {
		sRepository.save(newStock);
		return "Successfully added a new stock!";
	}

	public Stock getStockInfo(int stockId) {
		return sRepository.findById(stockId).orElse(null);
	}

	public List<Stock> getAllStocksBy1() {
		return (List<Stock>) sRepository.findStocksByCustomQuery();
	}
}
