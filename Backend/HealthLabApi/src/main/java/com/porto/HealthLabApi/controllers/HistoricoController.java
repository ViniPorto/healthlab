package com.porto.HealthLabApi.controllers;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.porto.HealthLabApi.domain.historico.DTO.ResponseHistorico;
import com.porto.HealthLabApi.services.HistoricoService;
import com.porto.HealthLabApi.utils.ResponseHandler;

@RestController
@RequestMapping("/historico")
public class HistoricoController {
    
    @Autowired
    private HistoricoService historicoService;

    @Autowired
    private ResponseHandler responseHandler;

    @GetMapping("/{tabela}/{referenciaId}")
    public ResponseEntity<?> listarHistoricos(@PageableDefault(size = 10, sort = {"acaoDataHora"}, direction = Direction.DESC) Pageable paginacao, 
                                              @PathVariable("tabela") String tabela, 
                                              @PathVariable("referenciaId") Long referenciaId){
        var historicos = historicoService.listarHistoricos(paginacao, tabela, referenciaId).stream().map(h -> new ResponseHistorico(h)).collect(Collectors.toList());

        return responseHandler.generateResponse("Consulta realizada com sucesso", true, HttpStatus.OK, historicos);
    }

    @GetMapping
    public ResponseEntity<?> listarTodosHistoricos(@PageableDefault(size = 10, sort = {"acaoDataHora"}, direction = Direction.DESC) Pageable paginacao){
        var historicos = historicoService.listarTodosHistoricos(paginacao).map(ResponseHistorico::new).toList();

        return responseHandler.generateResponse("Consulta realizada com sucesso", true, HttpStatus.OK, historicos);
    }
}
