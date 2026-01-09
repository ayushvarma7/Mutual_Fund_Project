package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.Investment;
import com.project.service.InvestmentService;

@RestController
public class InvestmentController {

	@Autowired
	InvestmentService is;

	@RequestMapping(value = "/investments", method = RequestMethod.GET)
	public List<Investment> getAllInvestments() {

		return is.getAllInvestments();
	}

	@RequestMapping(value = "/investments/add", method = RequestMethod.POST)
	public String addInvestments(@RequestBody Investment newInvestment) {

		return is.addAInvestment(newInvestment);
	}

	@RequestMapping(value = "/investments/id/{investorid}", method = RequestMethod.GET)
	public List<Investment> getInvestmentsById(@PathVariable int investorid) {
		return is.getInvestmentsById(investorid);
	}

	@RequestMapping(value = "/investments/investmentid/{investmentId}", method = RequestMethod.GET)
	public Investment getInvestmentbyInvestmentId(@PathVariable int investmentId) {
		return is.getInvestmentDetails(investmentId);
	}

	@RequestMapping(value = "/investments/getunits/{investorId}/{fundId}", method = RequestMethod.GET)
	public double getTotalUnitsForInvestorForAFund(@PathVariable int investorId, @PathVariable int fundId) {
		return is.getTotalUnitsForInvestorForAFund(investorId, fundId);
	}

}
