package com.volvo.assessment.piotrkuchnowski;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiClient {
    String apiKey;
    String baseForecastURL = "https://api.weatherapi.com/v1/forecast.json?key=";
    ObjectMapper objectMapper = new ObjectMapper();
    HttpClient client = HttpClient.newHttpClient();

    public ApiClient(String apiKey) {
        this.apiKey = apiKey;
    }

    public ForecastResponse getResponse(String location) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseForecastURL + apiKey + "&q=" + location + "&days=3&aqi=no&alerts=no"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return objectMapper.readValue(response.body(), ForecastResponse.class);
    }

}