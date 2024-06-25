package com.volvo.assessment.piotrkuchnowski.controller;

import com.volvo.assessment.piotrkuchnowski.response.ApiErrorResponse;
import com.volvo.assessment.piotrkuchnowski.response.CityForecast;
import com.volvo.assessment.piotrkuchnowski.service.CityWeatherService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/weather")
public class CityWeatherController {
    private final CityWeatherService cityWeatherService;

    @Autowired
    public CityWeatherController(CityWeatherService cityWeatherService) {
        this.cityWeatherService = cityWeatherService;
    }

    @Operation(
            description = "Get endpoint to get weather forecast for a city for the next 3 days.",
            responses = {
                @ApiResponse(
                        description = "City weather forecast returned successfully.",
                        responseCode = "200",
                        content =
                                @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = CityForecast.class))),
                @ApiResponse(
                        description = "Location not provided.",
                        responseCode = "400",
                        content =
                                @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = ApiErrorResponse.class))),
                @ApiResponse(
                        description = "API Key not provided.",
                        responseCode = "401",
                        content =
                                @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = ApiErrorResponse.class))),
                @ApiResponse(
                        description = "City not found.",
                        responseCode = "404",
                        content =
                                @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = ApiErrorResponse.class)))
            })
    @GetMapping("/city")
    public ResponseEntity<CityForecast> getCityWeather(@RequestParam String cityName)
            throws IOException, InterruptedException {
        System.out.println("cityName=" + cityName);
        CityForecast cityForecast = cityWeatherService.getCityWeather(cityName);
        return new ResponseEntity<CityForecast>(cityForecast, HttpStatus.OK);
    }
}
