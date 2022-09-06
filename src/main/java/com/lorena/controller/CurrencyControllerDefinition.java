package com.lorena.controller;

import com.lorena.model.ConversionResponse;
import com.lorena.model.RateResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotBlank;
import java.util.List;

public interface CurrencyControllerDefinition {

    @Operation(
            description ="Get the conversion from a currency A to a currency B.",
            method = "GET",
            parameters = {
                    @Parameter(name = "fromCurrency", in = ParameterIn.QUERY, schema = @Schema(type = "string"), example = "EUR"),
                    @Parameter(name = "toCurrency", in = ParameterIn.QUERY, schema = @Schema(type = "string"), example = "USD"),
                    @Parameter(name = "amount", in = ParameterIn.QUERY, schema = @Schema(type = "string"), example = "1200.05")
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK. The conversion has been succesfull ",
                            content = @Content(
                                    schema = @Schema(implementation = ConversionResponse.class)
                            )),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request ",
                            content = @Content(
                                    schema = @Schema(implementation = ConversionResponse.class)
                            )),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Unexpected internal server error",
                            content = @Content(
                                    schema = @Schema(implementation = ConversionResponse.class)
                            ))
            }
    )
    @GetMapping("/conversion")
    @ResponseBody
    ConversionResponse getConversion(@RequestParam @NotBlank String fromCurrency,
                                     @RequestParam @NotBlank String toCurrency,
                                     @RequestParam @NotBlank String amount);






    @Operation(
            description ="Get the conversion from a currency to a list of currencies.",
            method = "GET",
            parameters = {
                    @Parameter(name = "fromCurrency", in = ParameterIn.QUERY, schema = @Schema(type = "string"), example = "EUR"),
                    @Parameter(name = "toCurrencies", in = ParameterIn.QUERY, schema = @Schema(type = "array"), example = "USD, GBP"),
                    @Parameter(name = "amount", in = ParameterIn.QUERY, schema = @Schema(type = "string"), example = "1200")
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK. The conversion has been succesfull ",
                            content = @Content(
                                    schema = @Schema(implementation = ConversionResponse.class)
                            )),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request ",
                            content = @Content(
                                    schema = @Schema(implementation = ConversionResponse.class)
                            )),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Unexpected internal server error",
                            content = @Content(
                                    schema = @Schema(implementation = ConversionResponse.class)
                            ))
            }
    )
    @GetMapping(path = "/conversion/list")
    @ResponseBody
    List<ConversionResponse> getConversionForCurrencyList(@RequestParam String fromCurrency, @RequestParam List<String> toCurrencies, @RequestParam String amount);


    @Operation(
            description ="Get all the exchange rates from a specific currency.",
            method = "GET",
            parameters = {
                    @Parameter(name = "currency", in = ParameterIn.QUERY, schema = @Schema(type = "string"), example = "EUR")
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK. Exchange rate succesfully retrieved",
                            content = @Content(
                                    schema = @Schema(implementation = ConversionResponse.class)
                            )),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request ",
                            content = @Content(
                                    schema = @Schema(implementation = ConversionResponse.class)
                            )),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Unexpected internal server error",
                            content = @Content(
                                    schema = @Schema(implementation = ConversionResponse.class)
                            ))
            }
    )
    @GetMapping(path = "/exchange-rates")
    @ResponseBody
    RateResponse getAllExchangeRates(@RequestParam String currency);


    @Operation(
            description ="Get the exchange rate from currency A to currency B.",
            method = "GET",
            parameters = {
                    @Parameter(name = "baseCurrency", in = ParameterIn.QUERY, schema = @Schema(type = "string"), example = "EUR"),
                    @Parameter(name = "toCurrency", in = ParameterIn.QUERY, schema = @Schema(type = "string"), example = "USD")
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK. Exchange rate succesfully retrieved ",
                            content = @Content(
                                    schema = @Schema(implementation = ConversionResponse.class)
                            )),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request ",
                            content = @Content(
                                    schema = @Schema(implementation = ConversionResponse.class)
                            )),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Unexpected internal server error",
                            content = @Content(
                                    schema = @Schema(implementation = ConversionResponse.class)
                            ))
            }
    )
    @GetMapping("/exchange-rate")
    @ResponseBody
    RateResponse getExchangeRate(@RequestParam String baseCurrency, @RequestParam String toCurrency);


}
