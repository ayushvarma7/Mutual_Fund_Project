package com.project;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class StockController {

	@Autowired
	StockService ss;
	
	
	@RequestMapping(value="/stocks", method=RequestMethod.GET)
	public ArrayList<Stock> getAllStocks(){
		
		return ss.getAllStocks();
	}
	
	@RequestMapping(value="/stocks/add", method=RequestMethod.POST)
	public String addStocks(@RequestBody Stock newStock ){
	
		return ss.addStocks(newStock); 
	}
	
	
}
