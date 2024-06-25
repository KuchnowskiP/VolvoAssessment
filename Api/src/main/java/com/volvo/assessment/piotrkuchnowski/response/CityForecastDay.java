package com.volvo.assessment.piotrkuchnowski.response;

import java.math.BigDecimal;

public record CityForecastDay(
        String date,
        BigDecimal maxtemp_c,
        BigDecimal mintemp_c,
        BigDecimal avgtemp_c,
        BigDecimal maxwind_kph,
        BigDecimal totalprecip_mm,
        BigDecimal totalsnow_cm,
        BigDecimal avghumidity,
        Integer uv) {}
