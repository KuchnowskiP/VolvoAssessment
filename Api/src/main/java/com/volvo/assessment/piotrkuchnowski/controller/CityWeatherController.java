package com.volvo.assessment.piotrkuchnowski.controller;

import com.volvo.assessment.piotrkuchnowski.response.CityForecast;
import com.volvo.assessment.piotrkuchnowski.service.CityWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.Normalizer;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/apis/weather")
public class CityWeatherController {
    private final CityWeatherService cityWeatherService;

    @Autowired
    public CityWeatherController(CityWeatherService cityWeatherService) {
        this.cityWeatherService = cityWeatherService;
    }



    @GetMapping("/city/{cityName}")
    public ResponseEntity<CityForecast> getCityWeather(@PathVariable String cityName) throws IOException, InterruptedException {
        System.out.println("cityName="+cityName);
        CityForecast cityForecast = cityWeatherService.getCityWeather(cityName);
        if(cityForecast != null) {
            return ResponseEntity.ok(cityForecast);
        }
        return ResponseEntity.notFound().build();
    }
}
