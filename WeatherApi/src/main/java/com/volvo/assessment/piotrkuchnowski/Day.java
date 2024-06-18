package com.volvo.assessment.piotrkuchnowski;

import java.math.BigDecimal;

public record Day (
        BigDecimal maxtemp_c,
        BigDecimal maxtemp_f,
        BigDecimal mintemp_c,
        BigDecimal mintemp_f,
        BigDecimal avgtemp_c,
        BigDecimal avgtemp_f,
        BigDecimal maxwind_mph,
        BigDecimal maxwind_kph,
        BigDecimal totalprecip_mm,
        BigDecimal totalprecip_in,
        BigDecimal totalsnow_cm,
        BigDecimal avgvis_km,
        BigDecimal avgvis_miles,
        BigDecimal avghumidity,
        Integer daily_will_it_rain,
        BigDecimal daily_chance_of_rain,
        Integer daily_will_it_snow,
        BigDecimal daily_chance_of_snow,
        Condition condition,
        Integer uv
) {

}
