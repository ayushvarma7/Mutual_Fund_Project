package com.project.dto;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.DecimalMin;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class MutualFundRequest {
    @NotBlank(message = "Fund name is required")
    private String fundName;

    @DecimalMin(value = "0.0", message = "Expense ratio cannot be negative")
    private double expenseRatio;

    @DecimalMin(value = "0.01", message = "Current NAV must be greater than zero")
    private double currentNAV;

    @DecimalMin(value = "0.0", message = "Exit load cannot be negative")
    private double exitLoad;

    @NotEmpty(message = "At least one stock must be selected")
    private List<Map<String, Object>> selectedStocks;

    // Default constructor
    public MutualFundRequest() {
    }

    // Getters and Setters
    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public double getExpenseRatio() {
        return expenseRatio;
    }

    public void setExpenseRatio(double expenseRatio) {
        this.expenseRatio = expenseRatio;
    }

    public double getCurrentNAV() {
        return currentNAV;
    }

    public void setCurrentNAV(double currentNAV) {
        this.currentNAV = currentNAV;
    }

    public double getExitLoad() {
        return exitLoad;
    }

    public void setExitLoad(double exitLoad) {
        this.exitLoad = exitLoad;
    }

    public List<Map<String, Object>> getSelectedStocks() {
        return selectedStocks;
    }

    public void setSelectedStocks(List<Map<String, Object>> selectedStocks) {
        this.selectedStocks = selectedStocks;
    }
}
