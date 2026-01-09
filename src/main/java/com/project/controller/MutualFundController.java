package com.project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.StockInfo;
import com.project.dto.MutualFundRequest;
import com.project.dto.MutualFundResponse;
import com.project.service.MutualFundService;

@RestController
public class MutualFundController {

	@Autowired
	MutualFundService mfs;

	@RequestMapping(value = "/mutualfunds", method = RequestMethod.GET)
	public List<MutualFundResponse> getAllMutualFunds() {
		return mfs.getAllMutualFunds();
	}

	@RequestMapping(value = "/mutualfund/add", method = RequestMethod.POST)
	public ResponseEntity<MutualFundResponse> createMutualFund(@Valid @RequestBody MutualFundRequest request) {
		MutualFundResponse createdMutualFund = mfs.createMutualFundWithStocks(request);
		return ResponseEntity.ok(createdMutualFund);
	}

	@RequestMapping(value = "/mutualfunds/id/{mfid}", method = RequestMethod.GET)
	public MutualFundResponse getMutualFundInfo(@PathVariable int mfid) {
		return mfs.getMutualFundInfo(mfid);
	}

	@RequestMapping(value = "/mutualfund/getstockweights/{mfid}", method = RequestMethod.GET)
	public List<StockInfo> getStockComposition(@PathVariable int mfid) {
		return mfs.getStockComposition(mfid);
	}

	@RequestMapping(value = "/investor/mfs/{investorId}", method = RequestMethod.GET)
	public List<MutualFundResponse> getListOfInvestedMutualFundsByInvestor(@PathVariable int investorId) {
		return mfs.getListOfInvestedMutualFundsByInvestor(investorId);
	}

}
