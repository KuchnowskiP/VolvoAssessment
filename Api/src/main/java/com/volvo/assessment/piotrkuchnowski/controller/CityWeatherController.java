package com.volvo.assessment.piotrkuchnowski.controller;

import com.volvo.assessment.piotrkuchnowski.response.CityForecast;
import com.volvo.assessment.piotrkuchnowski.service.CityWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apis/weather/city")
public class CityWeatherController {
    private final CityWeatherService cityWeatherService;

    @Autowired
    public CityWeatherController(CityWeatherService cityWeatherService) {
        this.cityWeatherService = cityWeatherService;
    }

    @GetMapping("/wroclaw")
    public ResponseEntity<CityForecast> getWroclawWeather() {
        return null;
    }

    @GetMapping("/warsaw")
    public ResponseEntity<CityForecast> getWarsawWeather() {
        return null;
    }

    @GetMapping("/krakow")
    public ResponseEntity<CityForecast> getKrakowWeather() {
        return null;
    }

    @GetMapping("/lodz")
    public ResponseEntity<CityForecast> getLodzWeather() {
        return null;
    }

    @GetMapping("/poznan")
    public ResponseEntity<CityForecast> getPoznanWeather() {
        return null;
    }
}
