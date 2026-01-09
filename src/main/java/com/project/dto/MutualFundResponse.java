package com.project.dto;

import java.util.Date;
import org.springframework.hateoas.RepresentationModel;

public class MutualFundResponse extends RepresentationModel<MutualFundResponse> {
    private int fundId;
    private String fundName;
    private double assetsUnderManagement;
    private double currentNAV;
    private double expenseRatio;
    private double exitLoad;
    private Date inceptionDate;

    public MutualFundResponse() {
    }

    // Getters and Setters
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

    public Date getInceptionDate() {
        return inceptionDate;
    }

    public void setInceptionDate(Date inceptionDate) {
        this.inceptionDate = inceptionDate;
    }
}
