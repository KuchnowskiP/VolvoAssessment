package com.volvo.assessment.piotrkuchnowski.controller;

import com.volvo.assessment.piotrkuchnowski.exception.LocationNotFoundException;
import com.volvo.assessment.piotrkuchnowski.exception.LocationNotProvidedException;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;

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
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CityForecast.class)
                            )
                    ),
                    @ApiResponse(
                            description = "City not found.",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class)
                            )
                    )
            }
    )
    @GetMapping("/city/{cityName}")
    public ResponseEntity<?> getCityWeather(@PathVariable String cityName) throws IOException, InterruptedException {
        System.out.println("cityName="+cityName);
        try {
            CityForecast cityForecast = cityWeatherService.getCityWeather(cityName);
            return ResponseEntity.ok(cityForecast);
        }catch(LocationNotFoundException e){
            return new ResponseEntity<>(
                    new ApiErrorResponse(
                            LocalDateTime.now().toString(),
                            HttpStatus.NOT_FOUND.value(),
                            HttpStatus.NOT_FOUND.getReasonPhrase(),
                            e.getMessage(),
                            "/api/v1/weather/city/"+cityName
                    ), HttpStatus.NOT_FOUND
            );
        }catch(LocationNotProvidedException e){
            return new ResponseEntity<>(
                    new ApiErrorResponse(
                            LocalDateTime.now().toString(),
                            HttpStatus.BAD_REQUEST.value(),
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            e.getMessage(),
                            "/api/v1/weather/city/"+cityName
                    ), HttpStatus.BAD_REQUEST
            );
        }
    }
}
