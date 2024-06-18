package com.volvo.assessment.piotrkuchnowski;

import java.math.BigDecimal;

public record Current(
        Integer last_updated_epoch,
        String last_updated,
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
        BigDecimal vis_km,
        BigDecimal vis_miles,
        Integer uv,
        BigDecimal gust_mph,
        BigDecimal gust_kph
) {

}
