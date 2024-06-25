package com.volvo.assessment.piotrkuchnowski.handler;

import com.volvo.assessment.piotrkuchnowski.exception.DisabledApiKeyException;
import com.volvo.assessment.piotrkuchnowski.exception.LocationNotFoundException;
import com.volvo.assessment.piotrkuchnowski.exception.LocationNotProvidedException;
import com.volvo.assessment.piotrkuchnowski.response.ApiErrorResponse;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DisabledApiKeyException.class)
    public ResponseEntity<ApiErrorResponse> handleDisabledApiKeyException(DisabledApiKeyException e) {
        ApiErrorResponse response = new ApiErrorResponse(
                LocalDateTime.now().toString(),
                HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                e.getMessage(),
                "/api/v1/weather/city/"
        );
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(LocationNotProvidedException.class)
    public ResponseEntity<ApiErrorResponse> handleLocationNotProvidedException(LocationNotProvidedException e) {
        return new ResponseEntity<>(
                new ApiErrorResponse(
                        LocalDateTime.now().toString(),
                        HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        e.getMessage(),
                        "/api/v1/weather/city/"
                ), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(LocationNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleLocationNotFoundException(LocationNotFoundException e) {
        return new ResponseEntity<>(
                new ApiErrorResponse(
                        LocalDateTime.now().toString(),
                        HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND.getReasonPhrase(),
                        e.getMessage(),
                        "/api/v1/weather/city/"
                ), HttpStatus.NOT_FOUND
        );
    }
}
