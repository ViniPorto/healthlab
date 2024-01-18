package com.porto.HealthLabApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.porto.HealthLabApi.domain.relatorios.examesRealizadosPorPeriodo.DTO.RequestRelatorioExamesRealizadosPorPeriodo;
import com.porto.HealthLabApi.services.RelatoriosService;
import com.porto.HealthLabApi.utils.ResponseHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/relatorios")
public class RelatoriosController {

    @Autowired
    private ResponseHandler responseHandler;

    @Autowired
    private RelatoriosService relatoriosService;
    
    @PostMapping("/examesPorPeriodo")
    public ResponseEntity<?> gerarRelatorioExamesPorPeriodo(@RequestBody @Valid RequestRelatorioExamesRealizadosPorPeriodo dadosRelatorio){
        var relatorio = relatoriosService.gerarRelatorioExamesPorPeriodo(dadosRelatorio);
        
        return responseHandler.generateResponse("Consulta realizada com sucesso", true, HttpStatus.OK, relatorio);
    }

}
