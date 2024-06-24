package com.volvo.assessment.piotrkuchnowski.response;

import org.springframework.http.HttpStatus;

public record ApiErrorResponse(
        String timestamp,
        Integer statusCode,
        String error,
        String message,
        String path
){}
