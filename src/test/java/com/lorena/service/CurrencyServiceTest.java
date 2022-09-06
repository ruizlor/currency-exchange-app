package com.lorena.service;

import com.lorena.client.CurrencyApiClient;
import com.lorena.model.ConversionResponse;
import com.lorena.model.Query;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CurrencyServiceTest {

    @InjectMocks
    CurrencyServiceImp currencyService;
    @Mock
    CurrencyApiClient currencyApiClient;

    @Test
    public void testGetConversion() {
        ConversionResponse conversionResponse = new ConversionResponse(mock(Query.class), "1200");
        when(currencyApiClient.getConversion(anyString(), anyString(), anyString())).thenReturn(conversionResponse);
        assertEquals(currencyService.getConversion("EUR", "USD", "1200"), conversionResponse);
    }

    @Test
    public void testGetConversionForCurrencyList() {

        when(currencyApiClient.getConversion(anyString(), anyString(), anyString())).thenReturn(mock(ConversionResponse.class));

        assertThat(currencyService.getConversionForCurrencyList("EUR", List.of("GBP", "USD"), "1200"),
                Matchers.hasSize(2));
    }

    @Test
    public void testExchangeRates() {

        when(currencyApiClient.getConversion(anyString(), anyString(), anyString()))
                .thenReturn(mock(ConversionResponse.class));

        assertThat(currencyService.getConversionForCurrencyList("EUR", List.of("GBP", "USD"), "1200"),
                Matchers.hasSize(2));
    }

}
