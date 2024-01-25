package com.porto.HealthLabApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.porto.HealthLabApi.domain.laudo.RequestGerarLaudo;
import com.porto.HealthLabApi.services.LaudoService;
import com.porto.HealthLabApi.utils.ResponseHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/laudo")
public class LaudoController {
    
    @Autowired
    private ResponseHandler responseHandler;

    @Autowired
    private LaudoService laudoService;

    @PostMapping
    public ResponseEntity<?> gerarLaudos(@RequestBody @Valid RequestGerarLaudo dadosLaudo){
        var dadosLaudos = laudoService.gerarLaudos(dadosLaudo);

        return responseHandler.generateResponse("Gerado com sucesso", true, HttpStatus.OK, dadosLaudos);
    }
}
