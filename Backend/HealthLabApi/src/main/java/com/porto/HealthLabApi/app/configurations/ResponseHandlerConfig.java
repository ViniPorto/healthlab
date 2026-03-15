package com.porto.HealthLabApi.app.configurations;

import java.sql.Timestamp;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Data;

@Configuration
public class ResponseHandlerConfig {
    
    public ResponseEntity<ResponseObj> generateResponse(String message, boolean sucessful, String error, HttpStatus status, Object responseObj){
        ResponseObj response = this.generateMap(message, sucessful, error, status, responseObj);
        
		return new ResponseEntity<ResponseObj>(response, status);
    }

    private ResponseObj generateMap(String message, boolean sucessful, String error, HttpStatus status, Object data){
        ResponseObj response = new ResponseObj();
        response.setSucessful(sucessful);
        response.setTimestamp(new Timestamp(System.currentTimeMillis()).toString());
        response.setMessage(message);
        response.setStatus(status.value());
        if(data != null){
            response.setData(data);
        }
        if(error != null) {
            response.setError(error);
        }
        return response;
    }

    @Data
    public static class ResponseObj {
        Boolean sucessful;
        String timestamp;
        String message;
        Integer status;
        Object data;
        String error;
    }

}
