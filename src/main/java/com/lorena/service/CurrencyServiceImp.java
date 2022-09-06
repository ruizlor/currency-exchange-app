package com.lorena.service;

import com.lorena.client.CurrencyApiClient;
import com.lorena.model.ConversionResponse;
import com.lorena.model.RateResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyServiceImp implements CurrencyService{

    CurrencyApiClient currencyApiClient;

    public CurrencyServiceImp(CurrencyApiClient currencyApiClient) {
        this.currencyApiClient = currencyApiClient;
    }

    @Cacheable(value = "currencyCache")
    public ConversionResponse getConversion(String fromCurrency, String toCurrency, String amount) {
        return currencyApiClient.getConversion(fromCurrency, toCurrency, amount);
    }

    @Cacheable(value = "currencyCache")
    public List<ConversionResponse> getConversionForCurrencyList(String fromCurrency, List<String> toCurrencies, String amount) {
        return toCurrencies.stream().map(currency -> getConversion(fromCurrency, currency, amount))
                .collect(Collectors.toList());
    }

    @Cacheable(value = "currencyCache")
    public RateResponse getAllExchangeRates(String currency) {
        return currencyApiClient.getAllExchangeRates(currency);
    }

    @Cacheable(value = "currencyCache")
    public RateResponse getExchangeRate(String fromCurrency, String toCurrency) {
        return currencyApiClient.getExchangeRate(fromCurrency, toCurrency);
    }

}
