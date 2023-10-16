package com.porto.HealthLabApi.utils;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseHandler {
    
    public ResponseEntity<Object> generateResponse(String message, boolean sucessful, HttpStatus status, Object responseObj){
        var map = generateMap(message, sucessful, status, responseObj);
        
		return new ResponseEntity<Object>(map, status);
    }

    public Map<String, Object> generateMap(String message, boolean sucessful, HttpStatus status, Object responseObj){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sucessful", sucessful);
        map.put("timestamp", new Timestamp(System.currentTimeMillis()).toString());
        map.put("message", message);
        map.put("status", status.value());
        if(responseObj != null){
		    map.put("data", responseObj);
        }
        return map;
    }

}


