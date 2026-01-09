package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.MutualFund;
import com.project.model.PortfolioManager;
import com.project.service.PortfolioManagerService;

@RestController
public class PortfolioManagerController {

	@Autowired
	PortfolioManagerService pm;

	@RequestMapping(value = "/portfoliomanagers", method = RequestMethod.GET)
	public List<PortfolioManager> getAllPortfolioManagers() {
		return pm.getAllPortfolioManagers();
	}

	@RequestMapping(value = "/portfoliomanagers/add", method = RequestMethod.POST)
	public String addPortfolioManager(@RequestBody PortfolioManager newPortfolioManager) {

		System.out.println("inside the controller");
		return pm.addPortfolioManager(newPortfolioManager);
	}

	@RequestMapping(value = "/portfoliomanagers/getAllMF/{managerId}", method = RequestMethod.GET)
	public List<MutualFund> getAllMutualFunds(@PathVariable int managerId) {
		return pm.getAllMutualFunds(managerId);
	}

}
