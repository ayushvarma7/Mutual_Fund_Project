package com.project.model;

import java.io.Serializable;

public class StockIdentifier implements Serializable {

	public int fundId;
	public int stockId;

	public StockIdentifier() {
		super();

	}

	public StockIdentifier(int fundId, int stockId) {
		super();
		this.fundId = fundId;
		this.stockId = stockId;
	}

	public int getFundId() {
		return fundId;
	}

	public void setFundId(int fundId) {
		this.fundId = fundId;
	}

	public int getStockId() {
		return stockId;
	}

	public void setStockId(int stockId) {
		this.stockId = stockId;
	}

}
