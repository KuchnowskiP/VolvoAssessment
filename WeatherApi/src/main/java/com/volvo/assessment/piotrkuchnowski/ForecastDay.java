package com.volvo.assessment.piotrkuchnowski;

import java.util.List;

public record ForecastDay(String date, Integer date_epoch, Day day, Astro astro, List<Hour> hour) {}
