package com.volvo.assessment.piotrkuchnowski;

public record Astro(
        String sunrise,
        String sunset,
        String moonrise,
        String moonset,
        String moon_phase,
        String moon_illumination,
        Integer is_moon_up,
        Integer is_sun_up) {}
