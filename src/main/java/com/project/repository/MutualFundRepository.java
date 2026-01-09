package com.project.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.model.MutualFund;

import java.util.List;

public interface MutualFundRepository extends CrudRepository<MutualFund, Integer> {
    List<MutualFund> findByManagerId(int managerId);
}
