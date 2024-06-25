package com.volvo.assessment.piotrkuchnowski.response;

import java.util.List;

public record CityForecast(String name, List<CityForecastDay> forecastday) {}
