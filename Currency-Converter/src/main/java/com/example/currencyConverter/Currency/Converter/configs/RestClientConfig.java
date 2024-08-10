package com.example.currencyConverter.Currency.Converter.configs;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Configuration
public class RestClientConfig {

    private static final String FREE_API_BASE_URL = "https://api.freecurrencyapi.com/v1/latest?";
    @Value("${free.currency.api.key}")
    String Api_key;
//    https://api.freecurrencyapi.com/v1/latest?apikey=fca_live_xeitQzOVdHtNEq2Xor4nEniDCulHN1F4YK7F7KIZ&currencies=EUR%2CUSD%2CCAD&base_currency=CAD
//    https://api.freecurrencyapi.com/v1/latest?apikey=fca_live_xeitQzOVdHtNEq2Xor4nEniDCulHN1F4YK7F7KIZ&currencies=EUR&base_currency=CAD

    @Bean
    @Qualifier("freeCurrencyConverter")
    RestClient getCurrencyConvertedValue(String from, String to){
        String finalBaseUrl = FREE_API_BASE_URL+ Api_key + "&currencies" + to + "&base_currency"+from;
        return RestClient.builder().baseUrl(finalBaseUrl)
                .defaultHeader(CONTENT_TYPE,APPLICATION_JSON_VALUE)
                .build();
    }


}
