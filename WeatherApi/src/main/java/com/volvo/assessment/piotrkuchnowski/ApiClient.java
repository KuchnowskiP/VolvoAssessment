package com.volvo.assessment.piotrkuchnowski;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.volvo.assessment.piotrkuchnowski.exception.DisabledApiKeyException;
import com.volvo.assessment.piotrkuchnowski.exception.LocationNotFoundException;
import com.volvo.assessment.piotrkuchnowski.exception.LocationNotProvidedException;

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
        HttpRequest request =
                HttpRequest.newBuilder()
                        .uri(
                                URI.create(
                                        baseForecastURL
                                                + apiKey
                                                + "&q="
                                                + location
                                                + "&days=3&aqi=no&alerts=no"))
                        .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode responseJson = objectMapper.readTree(response.body());
        if (responseJson.has("error")) {
            System.out.println(location);
            System.out.println(responseJson.get("error").get("code").asInt());
            if (responseJson.get("error").get("code").asInt() == 1006) {
                throw new LocationNotFoundException("Location not found");
            }
            if (responseJson.get("error").get("code").asInt() == 1003) {
                throw new LocationNotProvidedException("Location not provided");
            }
            if (responseJson.get("error").get("code").asInt() == 2008) {
                throw new DisabledApiKeyException("Api key is disabled");
            }
        }
        return objectMapper.readValue(response.body(), ForecastResponse.class);
    }
}
