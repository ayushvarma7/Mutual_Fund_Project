package com.project.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.project.model.MutualFund;

@DataJpaTest
public class MutualFundRepositoryTest {

    @Autowired
    private MutualFundRepository mutualFundRepository;

    @Test
    void testSaveAndFindByManagerId() {
        MutualFund fund = new MutualFund();
        fund.setFundName("Repo Test Fund");
        fund.setManagerId(5);
        fund.setCurrentNAV(50.0);

        mutualFundRepository.save(fund);

        List<MutualFund> found = mutualFundRepository.findByManagerId(5);

        assertEquals(1, found.size());
        assertEquals("Repo Test Fund", found.get(0).getFundName());
    }

    @Test
    void testFindByManagerId_NotFound() {
        List<MutualFund> found = mutualFundRepository.findByManagerId(999);
        assertTrue(found.isEmpty());
    }
}
