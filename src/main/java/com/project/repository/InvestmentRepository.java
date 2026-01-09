package com.project.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.project.model.Investment;

public interface InvestmentRepository extends CrudRepository<Investment, Integer> {
	ArrayList<Investment> findByInvestorInvestorId(int investorId);

	Investment findByInvestmentId(int id);

	ArrayList<Investment> findByInvestorInvestorIdAndFundFundId(int investorId, int fundId);
}
