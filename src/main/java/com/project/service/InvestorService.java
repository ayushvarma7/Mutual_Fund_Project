package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.model.Investor;
import com.project.aspect.LogActivity;
import com.project.event.InvestorRegisteredEvent;
import com.project.repository.InvestorRepository;
import org.springframework.context.ApplicationEventPublisher;

@Service
public class InvestorService {

	@Autowired
	InvestorRepository iRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	public List<Investor> getAllInvestors() {
		return (List<Investor>) iRepository.findAll();
	}

	@LogActivity("Investor Registration")
	public String addInvestor(Investor newInvestor) {
		newInvestor.setPassword(bcryptEncoder.encode(newInvestor.getPassword()));
		iRepository.save(newInvestor);

		// Publish registration event for decoupled side-effects
		eventPublisher.publishEvent(new InvestorRegisteredEvent(this, newInvestor));

		return "Successfully registered the investor!";
	}

	public boolean emailExists(String email) {
		return iRepository.findByEmail(email) != null;
	}

	public Investor getInvestorInfo(int investorid) {
		return iRepository.findById(investorid).orElse(null);
	}

}
