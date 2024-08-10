package com.example.currencyConverter.Currency.Converter.controllers;

import com.example.currencyConverter.Currency.Converter.services.CurrencyConvertorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("convertCurrency")
@RequiredArgsConstructor
public class CurrencyConverterController {

    private final CurrencyConvertorService currencyConvertorService;

    @GetMapping
    public Integer getCurrencyConverted(
            @RequestParam(name = "fromCurrency") String from,
            @RequestParam(name = "toCurrency") String to
    ){
        return currencyConvertorService.getCurrencyConverted(from,to);
    }
}
