package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.MutualFund;
import com.project.model.PortfolioManager;
import com.project.repository.MutualFundRepository;
import com.project.repository.PortfolioManagerRepository;

@Service
public class PortfolioManagerService {

	@Autowired
	PortfolioManagerRepository pmRepository;
	@Autowired
	MutualFundRepository mfRepository;

	public String addPortfolioManager(PortfolioManager pm) {
		pmRepository.save(pm);
		return "Successfully inserted";
	}

	public List<PortfolioManager> getAllPortfolioManagers() {
		System.out.println("inside the service");
		return (List<PortfolioManager>) pmRepository.findAll();
	}

	public List<MutualFund> getAllMutualFunds(int managerId) {
		return mfRepository.findByManagerId(managerId);
	}

}
