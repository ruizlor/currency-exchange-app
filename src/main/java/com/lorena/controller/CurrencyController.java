package com.lorena.controller;

import com.lorena.model.ConversionResponse;
import com.lorena.model.RateResponse;
import com.lorena.service.CurrencyServiceImp;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
public class CurrencyController implements CurrencyControllerDefinition{

    private final CurrencyServiceImp currencyService;

    public CurrencyController(CurrencyServiceImp currencyService) {
        this.currencyService = currencyService;
    }

    @Override
    public ConversionResponse getConversion(@RequestParam String fromCurrency,
                                            @RequestParam String toCurrency,
                                            @RequestParam String amount) {
        return currencyService.getConversion(fromCurrency, toCurrency, amount);
    }

    @Override
    public List<ConversionResponse> getConversionForCurrencyList(@RequestParam String fromCurrency,
                                                                 @RequestParam List<String> toCurrencies,
                                                                 @RequestParam String amount) {
        return currencyService.getConversionForCurrencyList(fromCurrency, toCurrencies, amount);
    }

    @Override
    public RateResponse getAllExchangeRates(@RequestParam String currency) {
        return currencyService.getAllExchangeRates(currency);
    }

    @Override
    public RateResponse getExchangeRate(@RequestParam String fromCurrency,
                                        @RequestParam String toCurrency) {
        return currencyService.getExchangeRate(fromCurrency, toCurrency);
    }

}

