package com.volvo.assessment.piotrkuchnowski.service;

import com.volvo.assessment.piotrkuchnowski.ApiClient;
import com.volvo.assessment.piotrkuchnowski.ForecastDay;
import com.volvo.assessment.piotrkuchnowski.ForecastResponse;
import com.volvo.assessment.piotrkuchnowski.response.CityForecast;
import com.volvo.assessment.piotrkuchnowski.response.CityForecastDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class CityWeatherService {
    private final ApiClient apiClient;

    @Autowired
    public CityWeatherService(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    private String removeDiacritics(String cityName) {
        cityName = cityName.toLowerCase();
        cityName = cityName.replaceAll("ł", "l");
        cityName = Normalizer.normalize(cityName, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}");
        cityName = pattern.matcher(cityName).replaceAll("");
        return cityName;
    }

    private static CityForecastDay getCityForecastDay(ForecastDay forecastDay) {
        BigDecimal totalSnowMm = new BigDecimal(String.valueOf(forecastDay.day().totalsnow_cm())).multiply(new BigDecimal(10));
        CityForecastDay cityForecastDay = new CityForecastDay(
                forecastDay.date(),
                forecastDay.day().maxtemp_c(),
                forecastDay.day().mintemp_c(),
                forecastDay.day().avgtemp_c(),
                forecastDay.day().maxwind_kph(),
                forecastDay.day().totalprecip_mm(),
                totalSnowMm,
                forecastDay.day().avghumidity(),
                forecastDay.day().uv()
        );
        return cityForecastDay;
    }

    public CityForecast getCityWeather(String cityName) throws IOException, InterruptedException {
        cityName = removeDiacritics(cityName);

        ForecastResponse response = apiClient.getResponse(cityName);
        List<ForecastDay> forecastDays = response.forecast().forecastday();
        List<CityForecastDay> cityForecastDays = new ArrayList<>();

        for(ForecastDay forecastDay : forecastDays) {
            CityForecastDay cityForecastDay = getCityForecastDay(forecastDay);
            cityForecastDays.add(cityForecastDay);
        }

        String name = response.location().name();
        return new CityForecast(name, cityForecastDays);
    }


}
