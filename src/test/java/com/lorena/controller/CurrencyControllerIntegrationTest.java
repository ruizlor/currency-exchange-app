package com.lorena.controller;

import com.lorena.service.CurrencyServiceImp;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CurrencyControllerIntegrationTest {

    @Autowired
    CurrencyServiceImp currencyService;

    @Autowired
    MockMvc mockMvc;

    @WithMockUser
    @Test
    public void testConversion() throws Exception {

        mockMvc.perform(get("/conversion")
                        .param("fromCurrency", "USD")
                        .param("toCurrency", "EUR")
                        .param("amount", "1200"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").isNotEmpty());
    }



    @WithMockUser
    @Test
    public void testConversionForCurrencyList() throws Exception {
        mockMvc.perform(get("/conversion/list")
                        .param("fromCurrency", "USD")
                        .param("toCurrencies", "EUR, GBP")
                        .param("amount", "1200"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @WithMockUser
    @Test
    public void testGetAllExchangeRatesASingleFromCurrency() throws Exception {
        mockMvc.perform(get("/exchange-rates")
                        .param("currency", "USD"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rates").isNotEmpty())
                .andExpect(jsonPath("$.rates").isMap());
    }

    @WithMockUser
    @Test
    public void testGetExchangeRateFromBaseCurrencyToCurrency() throws Exception {
        mockMvc.perform(get("/exchange-rate")
                        .param("fromCurrency", "USD")
                        .param("toCurrency", "EUR"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rates").isNotEmpty())
                .andExpect(jsonPath("$.rates").value(Matchers.aMapWithSize(1)));
    }
}
