package com.porto.HealthLabApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.porto.HealthLabApi.domain.pessoa.DTO.RequestCadastrarPessoa;
import com.porto.HealthLabApi.domain.pessoa.DTO.RequestEditarPessoa;
import com.porto.HealthLabApi.domain.pessoa.DTO.ResponsePessoa;
import com.porto.HealthLabApi.services.PessoaService;
import com.porto.HealthLabApi.utils.ResponseHandler;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private ResponseHandler responseHandler;

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    private ResponseEntity<Object> listarPessoas(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        var pessoas = pessoaService.listarPessoas(paginacao).map(ResponsePessoa::new);

        
        return responseHandler.generateResponse("Consulta realizada com sucesso", true, HttpStatus.OK, pessoas);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Object> detalharPessoa(@PathVariable Long id){
        var pessoa =  pessoaService.detalharPessoa(id);

        return responseHandler.generateResponse("Consulta realizada com sucesso", true, HttpStatus.OK, pessoa);
    }

    @PostMapping
    @Transactional
    private ResponseEntity<Object> cadastrarPessoa(@RequestBody @Valid RequestCadastrarPessoa dadosPessoa){
        var pessoaCriada = pessoaService.cadastrarPessoa(dadosPessoa);

        return responseHandler.generateResponse("Cadastrado com sucesso", true, HttpStatus.CREATED, pessoaCriada);
    }

    @PutMapping
    @Transactional
    private ResponseEntity<Object> editarPessoa(@RequestBody @Valid RequestEditarPessoa dadosPessoa){
        var pessoaEditada = pessoaService.editarPessoa(dadosPessoa);

        return responseHandler.generateResponse("Editado com sucesso", true, HttpStatus.OK, pessoaEditada);
    }

    @DeleteMapping("/{id}")
    @Transactional
    private ResponseEntity<Object> deletarPessoa(@PathVariable Long id){
        pessoaService.deletarPessoa(id);

        return responseHandler.generateResponse("Deletado com sucesso", true, HttpStatus.OK, null);
    }

}
