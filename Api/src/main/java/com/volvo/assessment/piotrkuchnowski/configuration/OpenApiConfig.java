package com.volvo.assessment.piotrkuchnowski.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info =
                @Info(
                        contact =
                                @Contact(
                                        name = "Send email to Piotr Kuchnowski",
                                        email = "kuchnowski.piotr@outlook.com"),
                        description =
                                "This is a simple API to get weather forecast for a city. It uses OpenWeatherMap API to get the data.",
                        title = "Weather API for Volvo Assessment specification",
                        version = "1.0"),
        servers = {
            @Server(url = "http://localhost:8080", description = "Local server"),
        })
public class OpenApiConfig {}
