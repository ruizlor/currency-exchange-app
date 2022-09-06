package com.lorena.client;

import com.lorena.config.CurrencyExchangeApiConfig;
import com.lorena.model.ConversionResponse;
import com.lorena.model.RateResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Component
public class CurrencyApiClient {

    private final RestTemplate restTemplate;
    private final CurrencyExchangeApiConfig currencyExchangeApiConfig;

    public CurrencyApiClient(RestTemplate restTemplate, CurrencyExchangeApiConfig currencyExchangeApiConfig) {
        this.restTemplate = restTemplate;
        this.currencyExchangeApiConfig = currencyExchangeApiConfig;
    }


    public ConversionResponse getConversion(String fromCurrency, String toCurrency, String amount){
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(currencyExchangeApiConfig.getConversionEndpoint())
                .queryParam("from",fromCurrency)
                .queryParam("to",toCurrency)
                .queryParam("amount",amount).build();

        return restTemplate.getForObject(builder.toUriString(), ConversionResponse.class);
    }

    public RateResponse getConversion(String fromCurrency, List<String> toCurrencies, String amount){
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(currencyExchangeApiConfig.getConversionEndpoint())
                .queryParam("from",fromCurrency)
                .queryParam("to",toCurrencies)
                .queryParam("amount",amount).build();

        return restTemplate.getForObject(builder.toUriString(), RateResponse.class);
    }

    public RateResponse getAllExchangeRates(String currency){
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(currencyExchangeApiConfig.getRatesEndpoint())
                .queryParam("base",currency)
                .build();

        return restTemplate.getForObject(builder.toUriString(), RateResponse.class);
    }


    public RateResponse getExchangeRate(String fromCurrency, String toCurrency){
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(currencyExchangeApiConfig.getRatesEndpoint())
                .queryParam("base",fromCurrency)
                .queryParam("symbols",toCurrency)
                .build();

        return restTemplate.getForObject(builder.toUriString(), RateResponse.class);
    }



}
