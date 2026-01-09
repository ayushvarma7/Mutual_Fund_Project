package com.project.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.model.MutualFund;
import com.project.model.Stock;
import com.project.model.StockIdentifier;
import com.project.model.StockInfo;
import com.project.model.StocksInFund;
import com.project.aspect.LogActivity;
import com.project.controller.MutualFundController;
import com.project.dto.MutualFundRequest;
import com.project.dto.MutualFundResponse;
import com.project.exception.ResourceNotFoundException;
import com.project.repository.MutualFundRepository;
import com.project.repository.StockRepository;
import com.project.repository.StocksInFundRepository;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class MutualFundService {

	@Autowired
	MutualFundRepository mfRepository;
	@Autowired
	StocksInFundRepository sifRepository;
	@Autowired
	StockRepository sRepository;
	@Autowired
	InvestmentService ivService;

	@LogActivity("Retrieve All Mutual Funds")
	public List<MutualFundResponse> getAllMutualFunds() {
		List<MutualFundResponse> responses = new ArrayList<>();
		mfRepository.findAll().forEach(fund -> responses.add(mapToResponse(fund)));
		return responses;
	}

	@Transactional
	@LogActivity("Create Mutual Fund with Stocks")
	public MutualFundResponse createMutualFundWithStocks(MutualFundRequest request) {
		MutualFund fund = new MutualFund();
		fund.setFundName(request.getFundName());
		fund.setCurrentNAV(request.getCurrentNAV());
		fund.setExitLoad(request.getExitLoad());
		fund.setExpenseRatio(request.getExpenseRatio());
		fund.setAssetsUnderManagement(1000000000);
		fund.setManagerId(1);
		fund.setInceptionDate(new Date());

		MutualFund savedFund = mfRepository.save(fund);

		if (request.getSelectedStocks() != null) {
			for (Map<String, Object> stockMap : request.getSelectedStocks()) {
				int stockId = Integer.parseInt(stockMap.get("stockID").toString());
				double weightage = ((Number) stockMap.get("weightage")).doubleValue();

				Double stockPrice = sRepository.findStocksPriceById(stockId);
				if (stockPrice == null || stockPrice == 0)
					stockPrice = 1.0;

				double aum = 1000000000;
				double unit = (aum * weightage) / (100 * stockPrice);

				StockIdentifier stockIdentifier = new StockIdentifier(savedFund.getFundId(), stockId);
				StocksInFund stocksInFund = new StocksInFund(stockIdentifier, weightage, unit);

				sifRepository.save(stocksInFund);
			}
		}

		return mapToResponse(savedFund);
	}

	@LogActivity("Get Mutual Fund Info")
	public MutualFundResponse getMutualFundInfo(int mfid) {
		MutualFund fund = mfRepository.findById(mfid).orElse(null);
		if (fund == null) {
			throw new ResourceNotFoundException("Mutual Fund not found with id: " + mfid);
		}
		return mapToResponse(fund);
	}

	private MutualFundResponse mapToResponse(MutualFund fund) {
		MutualFundResponse response = new MutualFundResponse();
		response.setFundId(fund.getFundId());
		response.setFundName(fund.getFundName());
		response.setAssetsUnderManagement(fund.getAssetsUnderManagement());
		response.setCurrentNAV(fund.getCurrentNAV());
		response.setExpenseRatio(fund.getExpenseRatio());
		response.setExitLoad(fund.getExitLoad());
		response.setInceptionDate(fund.getInceptionDate());

		// Add HATEOAS links for discoverability
		response.add(linkTo(methodOn(MutualFundController.class).getMutualFundInfo(fund.getFundId())).withSelfRel());
		response.add(
				linkTo(methodOn(MutualFundController.class).getStockComposition(fund.getFundId())).withRel("stocks"));

		return response;
	}

	public List<StockInfo> getStockComposition(int mfid) {
		List<StockInfo> allStockInfo = new ArrayList<>();

		for (StocksInFund sif : sifRepository.findByFundFundId(mfid)) {
			StockInfo stockInfo = new StockInfo();
			stockInfo.setStockId(sif.getIdentifier().getStockId());
			stockInfo.setStockWeight(sif.getStockWeight());

			Stock stock = sif.getStock();
			if (stock != null) {
				stockInfo.setStockName(stock.getStockTicker());
				stockInfo.setClosingPrice(stock.getClosingPrice());
			}
			allStockInfo.add(stockInfo);
		}
		return allStockInfo;
	}

	public List<MutualFundResponse> getListOfInvestedMutualFundsByInvestor(int investorId) {
		Iterable<Integer> ids = ivService.getDistinctFundIdsByInvestorId(investorId);
		List<MutualFundResponse> responses = new ArrayList<>();
		mfRepository.findAllById(ids).forEach(fund -> responses.add(mapToResponse(fund)));
		return responses;
	}

}
