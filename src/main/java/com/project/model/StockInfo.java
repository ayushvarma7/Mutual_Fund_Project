package com.project.model;

public class StockInfo {

	int stockId;
	String stockName;
	double closingPrice;
	double stockWeight;

	public StockInfo() {
		super();

	}

	public StockInfo(int stockId, String stockName, double closingPrice, double stockWeight) {
		super();
		this.stockId = stockId;
		this.stockName = stockName;
		this.closingPrice = closingPrice;
		this.stockWeight = stockWeight;
	}

	public int getStockId() {
		return stockId;
	}

	public void setStockId(int stockId) {
		this.stockId = stockId;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public double getClosingPrice() {
		return closingPrice;
	}

	public void setClosingPrice(double closingPrice) {
		this.closingPrice = closingPrice;
	}

	public double getStockWeight() {
		return stockWeight;
	}

	public void setStockWeight(double stockWeight) {
		this.stockWeight = stockWeight;
	}

	@Override
	public String toString() {
		return "StockInfo [stockId=" + stockId + ", stockName=" + stockName + ", closingPrice=" + closingPrice
				+ ", stockWeight=" + stockWeight + "]";
	}

}
