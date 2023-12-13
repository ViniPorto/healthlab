package com.porto.HealthLabApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.porto.HealthLabApi.domain.agendamentoColeta.DTO.RequestCadastrarAgendamentoColeta;
import com.porto.HealthLabApi.domain.agendamentoColeta.DTO.ResponseAgendamentoColeta;
import com.porto.HealthLabApi.services.AgendamentoColetaService;
import com.porto.HealthLabApi.utils.ResponseHandler;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/agendamentoColeta")
public class AgendamentoColetaController {
    
    @Autowired
    private AgendamentoColetaService agendamentoColetaService;

    @Autowired
    private ResponseHandler responseHandler;

    @GetMapping
    public ResponseEntity<Object> listarAgendamentosColeta(@PageableDefault(size = 10, sort = {"dataHoraColeta"}, direction = Direction.DESC) Pageable paginacao,
                                                            @RequestParam(required = false) String nome,
                                                            @RequestParam(required = false) Long requisicaoId) {
        var agendamentosColeta = agendamentoColetaService.listarAgendamentosColeta(paginacao, nome, requisicaoId).map(ResponseAgendamentoColeta::new).toList();

        return responseHandler.generateResponse("Consulta realizada com sucesso", true, HttpStatus.OK, agendamentosColeta);
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarAgendamentoColeta(@RequestBody @Valid RequestCadastrarAgendamentoColeta dadosAgendamentoColeta){
        var agendamentoColeta = agendamentoColetaService.cadastrarAgendamentoColeta(dadosAgendamentoColeta);

        return responseHandler.generateResponse("Cadastrado com sucesso", true, HttpStatus.OK, new ResponseAgendamentoColeta(agendamentoColeta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarAgendamentoColeta(@PathVariable Long id){
        agendamentoColetaService.deletarAgendamentoColeta(id);

        return responseHandler.generateResponse("Deletado com sucesso", true, HttpStatus.OK, null);
    }
    
}
