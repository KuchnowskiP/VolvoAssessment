package com.volvo.assessment.piotrkuchnowski.response;

public record ApiErrorResponse(
        String timestamp, Integer statusCode, String error, String message, String path) {}
