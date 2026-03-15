package com.porto.HealthLabApi.app.configurations;

import java.sql.Timestamp;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Configuration
public class ResponseBuilderApi {
    
    public <T> ResponseEntity<ResponseObj<T>> generateResponse(String message, boolean sucessful, String error, HttpStatus status, T data){
        ResponseObj<T> response = this.generateMap(message, sucessful, error, status, data);
        
		return new ResponseEntity<>(response, status);
    }

    private <T> ResponseObj<T> generateMap(String message, boolean sucessful, String error, HttpStatus status, T data){
        ResponseObj<T> response = new ResponseObj<>();
        
        response.setSucessful(sucessful);
        response.setTimestamp(new Timestamp(System.currentTimeMillis()).toString());
        response.setMessage(message);
        response.setStatus(status.value());
        response.setData(data);
        response.setError(error);

        return response;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ResponseObj<T> {
        Boolean sucessful;
        String timestamp;
        String message;
        Integer status;
        T data;
        String error;
    }

}
