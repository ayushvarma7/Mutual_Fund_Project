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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;

@Entity
@Table(name = "Investment")
public class Investment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "investmentId", insertable = false, updatable = false)
	int investmentId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "investorId")
	private Investor investor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fundId")
	private MutualFund fund;

	double amountInvested;
	double units;

	@Column(name = "Transaction_Type")
	String transactionType;

	@Temporal(TemporalType.DATE)
	Date dateOfInvestment;

	@PrePersist
	protected void onCreate() {
		dateOfInvestment = new Date();
	}

	public Investment() {
		super();

	}

	public Investment(int investmentId, Investor investor, MutualFund fund, double amountInvested,
			String transactionType,
			double units,
			Date dateOfInvestment) {
		super();
		this.investmentId = investmentId;
		this.investor = investor;
		this.fund = fund;
		this.amountInvested = amountInvested;
		this.transactionType = transactionType;
		this.dateOfInvestment = dateOfInvestment;
		this.units = units;
	}

	public int getInvestmentId() {
		return investmentId;
	}

	public void setInvestmentId(int investmentId) {
		this.investmentId = investmentId;
	}

	public Investor getInvestor() {
		return investor;
	}

	public void setInvestor(Investor investor) {
		this.investor = investor;
	}

	public MutualFund getFund() {
		return fund;
	}

	public void setFund(MutualFund fund) {
		this.fund = fund;
	}

	public double getAmountInvested() {
		return amountInvested;
	}

	public void setAmountInvested(double amountInvested) {
		this.amountInvested = amountInvested;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Date getDateOfInvestment() {
		return dateOfInvestment;
	}

	public void setDateOfInvestment(Date dateOfInvestment) {
		this.dateOfInvestment = dateOfInvestment;
	}

	public double getUnits() {
		return units;
	}

	public void setUnits(double units) {
		this.units = units;
	}

	@Override
	public String toString() {
		return "Investment [investmentId=" + investmentId + ", amountInvested=" + amountInvested + ", units=" + units
				+ ", transactionType=" + transactionType
				+ ", dateOfInvestment=" + dateOfInvestment + "]";
	}

}
