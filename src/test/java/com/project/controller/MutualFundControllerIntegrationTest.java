package com.project.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.dto.MutualFundRequest;
import com.project.dto.MutualFundResponse;
import com.project.service.MutualFundService;

@SpringBootTest
@AutoConfigureMockMvc
public class MutualFundControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MutualFundService mutualFundService;

    @Autowired
    private ObjectMapper objectMapper;

    private MutualFundResponse fundResponse;

    @BeforeEach
    void setUp() {
        fundResponse = new MutualFundResponse();
        fundResponse.setFundId(1);
        fundResponse.setFundName("Growth Fund");
        fundResponse.setCurrentNAV(12.5);
    }

    @Test
    @WithMockUser(roles = "USER")
    void testGetAllMutualFunds_Success() throws Exception {
        List<MutualFundResponse> responses = new ArrayList<>();
        responses.add(fundResponse);
        when(mutualFundService.getAllMutualFunds()).thenReturn(responses);

        mockMvc.perform(get("/mutualfunds"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].fundName").value("Growth Fund"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testCreateMutualFund_AdminSuccess() throws Exception {
        MutualFundRequest request = createValidRequest();

        when(mutualFundService.createMutualFundWithStocks(any(MutualFundRequest.class))).thenReturn(fundResponse);

        mockMvc.perform(post("/mutualfund/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fundName").value("Growth Fund"));
    }

    @Test
    @WithMockUser(roles = "USER")
    void testCreateMutualFund_UserForbidden() throws Exception {
        MutualFundRequest request = createValidRequest();

        mockMvc.perform(post("/mutualfund/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isForbidden());
    }

    private MutualFundRequest createValidRequest() {
        MutualFundRequest request = new MutualFundRequest();
        request.setFundName("New Fund");
        request.setCurrentNAV(10.0);
        request.setExpenseRatio(0.5);
        request.setExitLoad(1.0);

        List<Map<String, Object>> stocks = new ArrayList<>();
        Map<String, Object> stock = new HashMap<>();
        stock.put("stockID", 101);
        stock.put("weightage", 100.0);
        stocks.add(stock);
        request.setSelectedStocks(stocks);
        return request;
    }
}
