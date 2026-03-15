package com.porto.HealthLabApi.domain.authentication.exceptions;

public class InvalidUsernameOrPasswordException extends Exception {
    
    public InvalidUsernameOrPasswordException(String message) {
        super(message);
    }

}
