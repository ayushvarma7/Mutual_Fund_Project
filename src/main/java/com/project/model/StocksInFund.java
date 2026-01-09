package com.project.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "STOCKSINFUND")
public class StocksInFund {

	@EmbeddedId
	StockIdentifier identifier;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("fundId")
	@JoinColumn(name = "fundId")
	private MutualFund fund;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("stockId")
	@JoinColumn(name = "stockId")
	private Stock stock;

	double stockWeight;
	double unit;

	public double getUnit() {
		return unit;
	}

	public void setUnit(double unit) {
		this.unit = unit;
	}

	public StocksInFund() {
		super();
	}

	public StocksInFund(StockIdentifier identifier, double stockWeight, double unit) {
		super();
		this.identifier = identifier;
		this.stockWeight = stockWeight;
		this.unit = unit;
	}

	public StockIdentifier getIdentifier() {
		return identifier;
	}

	public void setIdentifier(StockIdentifier identifier) {
		this.identifier = identifier;
	}

	public MutualFund getFund() {
		return fund;
	}

	public void setFund(MutualFund fund) {
		this.fund = fund;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public double getStockWeight() {
		return stockWeight;
	}

	public void setStockWeight(double stockWeight) {
		this.stockWeight = stockWeight;
	}

}
