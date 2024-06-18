package com.volvo.assessment.piotrkuchnowski;

import java.math.BigDecimal;

public record Hour(
        Integer time_epoch,
        String time,
        BigDecimal temp_c,
        BigDecimal temp_f,
        Integer is_day,
        Condition condition,
        BigDecimal wind_mph,
        BigDecimal wind_kph,
        BigDecimal wind_degree,
        String wind_dir,
        BigDecimal pressure_mb,
        BigDecimal pressure_in,
        BigDecimal precip_mm,
        BigDecimal precip_in,
        BigDecimal snow_cm,
        BigDecimal humidity,
        BigDecimal cloud,
        BigDecimal feelslike_c,
        BigDecimal feelslike_f,
        BigDecimal windchill_c,
        BigDecimal windchill_f,
        BigDecimal heatindex_c,
        BigDecimal heatindex_f,
        BigDecimal dewpoint_c,
        BigDecimal dewpoint_f,
        Integer will_it_rain,
        BigDecimal chance_of_rain,
        Integer will_it_snow,
        BigDecimal chance_of_snow,
        BigDecimal vis_km,
        BigDecimal vis_miles,
        BigDecimal gust_mph,
        BigDecimal gust_kph,
        Integer uv
) {

}
