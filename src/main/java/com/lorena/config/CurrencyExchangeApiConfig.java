package com.lorena.config;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Component
@ConfigurationProperties(prefix="currency-exchange-api")
public class CurrencyExchangeApiConfig {
    private String ratesEndpoint;
    private String conversionEndpoint;

}
