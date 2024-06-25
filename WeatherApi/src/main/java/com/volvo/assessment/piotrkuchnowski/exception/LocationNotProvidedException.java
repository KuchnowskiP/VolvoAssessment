package com.volvo.assessment.piotrkuchnowski.exception;

public class LocationNotProvidedException extends RuntimeException{
    public LocationNotProvidedException(String message) {
        super(message);
    }
}