package com.porto.HealthLabApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.porto.HealthLabApi.services.IndicadoresService;
import com.porto.HealthLabApi.utils.ResponseHandler;

@RestController
@RequestMapping("/indicadores")
public class IndicadoresController {
    
    @Autowired
    private IndicadoresService indicadoresService;

    @Autowired
    private ResponseHandler responseHandler;

    @GetMapping
    public ResponseEntity<Object> consultarIndicadores(){
        var indicadores = indicadoresService.consultarIndicadores();

        return responseHandler.generateResponse("Consulta realizada com sucesso", true, HttpStatus.OK, indicadores);
    }

}
