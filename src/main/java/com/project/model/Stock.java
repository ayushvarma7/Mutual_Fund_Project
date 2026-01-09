package com.project.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Stock")
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stockId", insertable = false)
	int stockId;
	String stockTicker;
	double openingPrice, lowPrice, closingPrice, highPrice;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-DD")
	Date dateOfRecord;

	@PrePersist
	protected void onCreate() {
		dateOfRecord = new Date();
	}

	public Stock() {
		super();

	}

	public Stock(int stockID, String stockTicker, Date dateOfRecord, double openingPrice, double lowPrice,
			double closingPrice, double highPrice) {
		super();
		this.stockId = stockID;
		this.stockTicker = stockTicker;
		this.dateOfRecord = dateOfRecord;
		this.openingPrice = openingPrice;
		this.lowPrice = lowPrice;
		this.closingPrice = closingPrice;
		this.highPrice = highPrice;
	}

	public int getStockID() {
		return stockId;
	}

	public void setStockID(int stockID) {
		this.stockId = stockID;
	}

	public String getStockTicker() {
		return stockTicker;
	}

	public void setStockTicker(String stockTicker) {
		this.stockTicker = stockTicker;
	}

	public Date getDateOfRecord() {
		return dateOfRecord;
	}

	public void setDateOfRecord(Date dateOfRecord) {
		this.dateOfRecord = dateOfRecord;
	}

	public double getOpeningPrice() {
		return openingPrice;
	}

	public void setOpeningPrice(double openingPrice) {
		this.openingPrice = openingPrice;
	}

	public double getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(double lowPrice) {
		this.lowPrice = lowPrice;
	}

	public double getClosingPrice() {
		return closingPrice;
	}

	public void setClosingPrice(double closingPrice) {
		this.closingPrice = closingPrice;
	}

	public double getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(double highPrice) {
		this.highPrice = highPrice;
	}

	// public static getStockInfo(int stockID) {
	// Stock st= new Stock();
	//
	// }

}
