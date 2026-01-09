package com.project.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Investment;
import com.project.aspect.LogActivity;
import com.project.repository.InvestmentRepository;

@Service
public class InvestmentService {

	@Autowired
	InvestmentRepository ivRepository;

	public List<Investment> getAllInvestments() {
		return (List<Investment>) ivRepository.findAll();
	}

	@LogActivity("Add New Investment")
	public String addAInvestment(Investment newInvestment) {
		ivRepository.save(newInvestment);
		return "Successfully added a new investment!";
	}

	public Investment getInvestmentDetails(int investmentId) {
		return ivRepository.findByInvestmentId(investmentId);
	}

	public List<Investment> getInvestmentsById(int investorid) {
		return ivRepository.findByInvestorInvestorId(investorid);
	}

	public List<Investment> getAllInvestmentsByInvestorForAFund(int investorId, int fundId) {
		return ivRepository.findByInvestorInvestorIdAndFundFundId(investorId, fundId);
	}

	// returns the total unit for a mf
	public double getTotalUnitsForInvestorForAFund(int investorId, int fundId) {
		List<Investment> investments = getAllInvestmentsByInvestorForAFund(investorId, fundId);
		// System.out.println(investments);
		double totalUnits = 0;
		for (Investment investment : investments) {
			totalUnits += investment.getUnits();
		}

		return totalUnits;

	}

	// return all unique mutual fund invested in
	public Set<Integer> getDistinctFundIdsByInvestorId(Integer investorId) {
		List<Investment> investments = ivRepository.findByInvestorInvestorId(investorId);
		return investments.stream()
				.map(investment -> investment.getFund().getFundId())
				.collect(Collectors.toSet());
	}

}
