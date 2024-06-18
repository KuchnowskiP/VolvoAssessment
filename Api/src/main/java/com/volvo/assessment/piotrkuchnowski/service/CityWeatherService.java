package com.volvo.assessment.piotrkuchnowski.service;

import com.volvo.assessment.piotrkuchnowski.ApiClient;
import com.volvo.assessment.piotrkuchnowski.ForecastResponse;
import com.volvo.assessment.piotrkuchnowski.response.CityForecast;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CityWeatherService {
    private ApiClient apiClient;

    public CityForecast getCityWeather(String cityName) throws IOException, InterruptedException {
        ForecastResponse response = apiClient.getResponse(cityName);
        return null;
    }
}
