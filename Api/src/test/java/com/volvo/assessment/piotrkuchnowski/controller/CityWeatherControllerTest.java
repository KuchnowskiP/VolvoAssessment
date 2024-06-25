package com.volvo.assessment.piotrkuchnowski.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.volvo.assessment.piotrkuchnowski.exception.DisabledApiKeyException;
import com.volvo.assessment.piotrkuchnowski.exception.LocationNotFoundException;
import com.volvo.assessment.piotrkuchnowski.exception.LocationNotProvidedException;
import com.volvo.assessment.piotrkuchnowski.response.CityForecast;
import com.volvo.assessment.piotrkuchnowski.service.CityWeatherService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CityWeatherControllerTest {
    @LocalServerPort private int port;

    @Autowired private TestRestTemplate restTemplate;

    @MockBean private CityWeatherService cityWeatherService;

    @Test
    void testWithoutProvidingCityName() throws IOException, InterruptedException {
        when(cityWeatherService.getCityWeather(""))
                .thenThrow(new LocationNotProvidedException("City name not provided"));
        ResponseEntity<String> response =
                restTemplate.getForEntity(
                        "http://localhost:" + port + "/api/v1/weather/city?cityName=",
                        String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testWithNonExistingCityName() throws IOException, InterruptedException {
        String nonExistingCity = "NonExistingCity";
        when(cityWeatherService.getCityWeather(nonExistingCity))
                .thenThrow(new LocationNotFoundException("City not found"));
        ResponseEntity<String> response =
                restTemplate.getForEntity(
                        "http://localhost:"
                                + port
                                + "/api/v1/weather/city?cityName="
                                + nonExistingCity,
                        String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testWithoutProvidingApiKey() throws IOException, InterruptedException {
        String city = "Warsaw";
        when(cityWeatherService.getCityWeather(city))
                .thenThrow(new DisabledApiKeyException("API Key not provided"));
        ResponseEntity<String> response =
                restTemplate.getForEntity(
                        "http://localhost:" + port + "/api/v1/weather/city?cityName=" + city,
                        String.class);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    void testWithExistingCityName() throws IOException, InterruptedException {
        String city = "Warsaw";
        when(cityWeatherService.getCityWeather(city))
                .thenReturn(
                        new CityForecast("City weather forecast for Warsaw", new ArrayList<>(0)));
        ResponseEntity<CityForecast> response =
                restTemplate.getForEntity(
                        "http://localhost:" + port + "/api/v1/weather/city?cityName=" + city,
                        CityForecast.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(
                "City weather forecast for Warsaw",
                Objects.requireNonNull(response.getBody()).name());
    }
}
