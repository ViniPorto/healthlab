package com.porto.HealthLabApi.domain.authentication.exceptions;

public class InvalidTokenException extends Exception {
    
    public InvalidTokenException(String message) {
        super(message);
    }

}
