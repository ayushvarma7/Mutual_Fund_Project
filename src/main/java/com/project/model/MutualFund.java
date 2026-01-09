package com.project.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MUTUALFUND")
public class MutualFund {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fundId", insertable = false, updatable = false)
	int fundId;
	String fundName;
	double assetsUnderManagement, currentNAV, expenseRatio, exitLoad;
	int managerId;
	Date inceptionDate;

	public MutualFund() {
		super();
	}

	public MutualFund(int fundId, String fundName, double assetsUnderManagement, double currentNAV, double expenseRatio,
			double exitLoad, int managerId, Date inceptionDate) {
		super();
		this.fundId = fundId;
		this.fundName = fundName;
		this.assetsUnderManagement = assetsUnderManagement;
		this.currentNAV = currentNAV;
		this.expenseRatio = expenseRatio;
		this.exitLoad = exitLoad;
		this.managerId = managerId;
		this.inceptionDate = inceptionDate;
	}

	public int getFundId() {
		return fundId;
	}

	public void setFundId(int fundId) {
		this.fundId = fundId;
	}

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public double getAssetsUnderManagement() {
		return assetsUnderManagement;
	}

	public void setAssetsUnderManagement(double assetsUnderManagement) {
		this.assetsUnderManagement = assetsUnderManagement;
	}

	public double getCurrentNAV() {
		return currentNAV;
	}

	public void setCurrentNAV(double currentNAV) {
		this.currentNAV = currentNAV;
	}

	public double getExpenseRatio() {
		return expenseRatio;
	}

	public void setExpenseRatio(double expenseRatio) {
		this.expenseRatio = expenseRatio;
	}

	public double getExitLoad() {
		return exitLoad;
	}

	public void setExitLoad(double exitLoad) {
		this.exitLoad = exitLoad;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public Date getInceptionDate() {
		return inceptionDate;
	}

	public void setInceptionDate(Date inceptionDate) {
		this.inceptionDate = inceptionDate;
	}

	@Override
	public String toString() {
		return "MutualFund [fundId=" + fundId + ", fundName=" + fundName + ", assetsUnderManagement="
				+ assetsUnderManagement + ", currentNAV=" + currentNAV + ", expenseRatio=" + expenseRatio
				+ ", exitLoad=" + exitLoad + ", managerId=" + managerId + ", inceptionDate=" + inceptionDate + "]";
	}

}
