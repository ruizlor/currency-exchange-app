package com.lorena;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableCaching
@SpringBootApplication
public class CurrencyExchangeAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyExchangeAppApplication.class, args);
	}

}
