package com.porto.HealthLabApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.porto.HealthLabApi.domain.pessoa.DTO.RequestCadastrarPessoa;
import com.porto.HealthLabApi.domain.pessoa.DTO.RequestEditarPessoa;
import com.porto.HealthLabApi.domain.pessoa.DTO.ResponsePessoa;
import com.porto.HealthLabApi.domain.usuario.Usuario;
import com.porto.HealthLabApi.services.PessoaService;
import com.porto.HealthLabApi.utils.ResponseHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private ResponseHandler responseHandler;

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public ResponseEntity<?> listarPessoas(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao,
                                                @RequestParam(required = false) String nome,
                                                @RequestParam(required = false) String cpf){
        var pessoas = pessoaService.listarPessoas(paginacao, nome, cpf).map(ResponsePessoa::new).toList();

        
        return responseHandler.generateResponse("Consulta realizada com sucesso", true, HttpStatus.OK, pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalharPessoa(@PathVariable Long id){
        var pessoa =  pessoaService.detalharPessoa(id);

        return responseHandler.generateResponse("Consulta realizada com sucesso", true, HttpStatus.OK, new ResponsePessoa(pessoa));
    }

    @PostMapping
    public ResponseEntity<?> cadastrarPessoa(@RequestBody @Valid RequestCadastrarPessoa dadosPessoa,
                                                  @AuthenticationPrincipal Usuario usuario){
        var pessoaCriada = pessoaService.cadastrarPessoa(dadosPessoa, usuario);

        return responseHandler.generateResponse("Cadastrado com sucesso", true, HttpStatus.CREATED, new ResponsePessoa(pessoaCriada));
    }

    @PutMapping
    public ResponseEntity<?> editarPessoa(@RequestBody @Valid RequestEditarPessoa dadosPessoa,
                                               @AuthenticationPrincipal Usuario usuario){
        var pessoaEditada = pessoaService.editarPessoa(dadosPessoa, usuario);

        return responseHandler.generateResponse("Editado com sucesso", true, HttpStatus.OK, new ResponsePessoa(pessoaEditada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPessoa(@PathVariable Long id){
        pessoaService.deletarPessoa(id);

        return responseHandler.generateResponse("Deletado com sucesso", true, HttpStatus.OK, null);
    }

}
