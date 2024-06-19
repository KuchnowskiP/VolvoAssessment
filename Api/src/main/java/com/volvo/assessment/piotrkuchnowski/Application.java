package com.volvo.assessment.piotrkuchnowski;

import com.volvo.assessment.piotrkuchnowski.controller.CityWeatherController;
import com.volvo.assessment.piotrkuchnowski.service.CityWeatherService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}
