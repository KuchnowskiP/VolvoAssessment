package com.volvo.assessment.piotrkuchnowski.configuration;

import com.volvo.assessment.piotrkuchnowski.ApiClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiClientConfigurator {
    private static String WEATHER_API_KEY;

    @Bean
    public ApiClient apiClient() {
        WEATHER_API_KEY = System.getenv("WEATHER_API_KEY");
        return new ApiClient(WEATHER_API_KEY);
    }
}
