package com.volvo.assessment.piotrkuchnowski;

import java.util.List;

public record Forecast (
        List<ForecastDay> forecastday
) {}
