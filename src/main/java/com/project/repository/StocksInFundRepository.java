package com.project.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.model.StocksInFund;

import java.util.List;
import com.project.model.StockIdentifier;

public interface StocksInFundRepository extends CrudRepository<StocksInFund, StockIdentifier> {
    List<StocksInFund> findByFundFundId(int fundId);
}
