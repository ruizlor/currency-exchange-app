package com.lorena.config;

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;


import java.time.Duration;

@Configuration
public class RedisConfiguration {

    private final CacheConfig cacheConfig;

    public RedisConfiguration(CacheConfig cacheConfig) {
        this.cacheConfig = cacheConfig;
    }

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return (builder) -> builder
                .withCacheConfiguration(cacheConfig.getName(),
                        RedisCacheConfiguration.defaultCacheConfig()
                                .entryTtl(Duration.ofMinutes(cacheConfig.getTtl()))
                                .disableCachingNullValues());
    }
}
