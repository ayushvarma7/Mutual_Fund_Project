package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.Investor;
import com.project.service.InvestorService;

@RestController
public class InvestorController {

	@Autowired
	InvestorService is;

	@RequestMapping(value = "/investors", method = RequestMethod.GET)
	public List<Investor> getAllInvestors() {
		return is.getAllInvestors();
	}

	@RequestMapping(value = "/investors/id/{investorid}", method = RequestMethod.GET)
	public Investor getInvestorInfor(@PathVariable int investorid) {
		return is.getInvestorInfo(investorid);
	}

}
