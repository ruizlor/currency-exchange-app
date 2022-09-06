package com.lorena.service;

import com.lorena.config.CacheConfig;
import com.lorena.model.ConversionResponse;
import com.lorena.model.RateResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.SimpleKey;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@EnableCaching
@SpringBootTest

public class CurrencyServiceCacheTest {

    @Autowired
    CacheManager cacheManager;

    @Autowired
    CacheConfig cacheConfig;

    @Autowired
    CurrencyServiceImp currencyService;

    private static final String DEFAULT_BASE_CURRENCY = "USD";
    private static final String DEFAULT_TO_CURRENCY = "EUR";
    private static final String DEFAULT_AMOUNT = "1000";


    @AfterEach
    void deleteCache(){
        cacheManager.getCache(cacheConfig.getName()).clear();
    }

    private ConversionResponse getCachedConversionFromRedis(SimpleKey key) {
        Cache cache = cacheManager.getCache(cacheConfig.getName());
        return cache.get(key, ConversionResponse.class);
    }

    private RateResponse getCachedRateFromRedis(SimpleKey key) {
        Cache cache = cacheManager.getCache(cacheConfig.getName());
        return cache.get(key, RateResponse.class);
    }

    @Test
    void testIf_ConversionIsPutInCache() {
        SimpleKey redisKey = new SimpleKey(DEFAULT_BASE_CURRENCY, DEFAULT_TO_CURRENCY, DEFAULT_AMOUNT);

        ConversionResponse conversion = currencyService.getConversion(DEFAULT_BASE_CURRENCY,
                DEFAULT_TO_CURRENCY,
                DEFAULT_AMOUNT);

        assertEquals(conversion, getCachedConversionFromRedis(redisKey));
    }

    @Test
    void testIf_ExchangeRateIsPutInCache() {
        SimpleKey redisKey = new SimpleKey(DEFAULT_BASE_CURRENCY, DEFAULT_TO_CURRENCY);

        RateResponse rate = currencyService.getExchangeRate(DEFAULT_BASE_CURRENCY,
                DEFAULT_TO_CURRENCY);

        assertEquals(rate, getCachedRateFromRedis(redisKey));
    }


}
