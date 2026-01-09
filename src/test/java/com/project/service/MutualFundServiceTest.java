package com.project.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.project.model.MutualFund;
import com.project.dto.MutualFundRequest;
import com.project.dto.MutualFundResponse;
import com.project.exception.ResourceNotFoundException;
import com.project.repository.MutualFundRepository;
import com.project.repository.StockRepository;
import com.project.repository.StocksInFundRepository;

@ExtendWith(MockitoExtension.class)
public class MutualFundServiceTest {

    @Mock
    private MutualFundRepository mfRepository;

    @Mock
    private StocksInFundRepository sifRepository;

    @Mock
    private StockRepository sRepository;

    @Mock
    private InvestmentService ivService;

    @InjectMocks
    private MutualFundService mutualFundService;

    private MutualFund fund;
    private MutualFundRequest request;

    @BeforeEach
    void setUp() {
        fund = new MutualFund();
        fund.setFundId(1);
        fund.setFundName("Test Fund");
        fund.setCurrentNAV(10.0);

        request = new MutualFundRequest();
        request.setFundName("New Fund");
        request.setCurrentNAV(15.0);
        request.setExitLoad(1.0);
        request.setExpenseRatio(0.5);

        List<Map<String, Object>> stocks = new ArrayList<>();
        Map<String, Object> stock1 = new HashMap<>();
        stock1.put("stockID", 101);
        stock1.put("weightage", 50.0);
        stocks.add(stock1);
        request.setSelectedStocks(stocks);
    }

    @Test
    void testGetMutualFundInfo_Success() {
        when(mfRepository.findById(1)).thenReturn(Optional.of(fund));

        MutualFundResponse response = mutualFundService.getMutualFundInfo(1);

        assertNotNull(response);
        assertEquals("Test Fund", response.getFundName());
        verify(mfRepository, times(1)).findById(1);
    }

    @Test
    void testGetMutualFundInfo_NotFound() {
        when(mfRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            mutualFundService.getMutualFundInfo(99);
        });
    }

    @Test
    void testCreateMutualFundWithStocks() {
        when(mfRepository.save(any(MutualFund.class))).thenReturn(fund);
        when(sRepository.findStocksPriceById(101)).thenReturn(100.0);

        MutualFundResponse response = mutualFundService.createMutualFundWithStocks(request);

        assertNotNull(response);
        verify(mfRepository, times(1)).save(any(MutualFund.class));
        verify(sifRepository, times(1)).save(any());
    }
}
