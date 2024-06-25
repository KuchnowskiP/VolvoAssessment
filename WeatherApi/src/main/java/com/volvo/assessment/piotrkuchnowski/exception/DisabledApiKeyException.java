package com.volvo.assessment.piotrkuchnowski.exception;

public class DisabledApiKeyException extends RuntimeException {
    public DisabledApiKeyException(String message) {
        super(message);
    }
}
