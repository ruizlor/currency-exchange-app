package com.lorena.service;

import com.lorena.model.ConversionResponse;
import com.lorena.model.RateResponse;

import java.util.List;


public interface CurrencyService {

    ConversionResponse getConversion(String fromCurrency, String toCurrency, String amount);

    List<ConversionResponse> getConversionForCurrencyList(String fromCurrency, List<String> toCurrencies, String amount);

    RateResponse getAllExchangeRates(String currency);

    RateResponse getExchangeRate(String fromCurrency, String toCurrency);

}
