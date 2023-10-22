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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.porto.HealthLabApi.domain.metodo.DTO.RequestCadastrarMetodo;
import com.porto.HealthLabApi.domain.metodo.DTO.RequestEditarMetodo;
import com.porto.HealthLabApi.domain.metodo.DTO.ResponseMetodo;
import com.porto.HealthLabApi.services.MetodoService;
import com.porto.HealthLabApi.utils.ResponseHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/metodo")
public class MetodoController {
    
    @Autowired
    private MetodoService metodoService;

    @Autowired
    private ResponseHandler responseHandler;

    @GetMapping
    public ResponseEntity<Object> listarMetodos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao,
                                                @RequestParam(required = false) String nome){
        var metodos = metodoService.listarMetodos(paginacao, nome).map(ResponseMetodo::new);

        return responseHandler.generateResponse("Consulta realizada com sucesso", true, HttpStatus.OK, metodos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> detalharMetodo(@PathVariable Long id){
        var metodo = metodoService.detalharMetodo(id);

        return responseHandler.generateResponse("Consulta realizada com sucesso", true, HttpStatus.OK, new ResponseMetodo(metodo));
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarMetodo(@RequestBody @Valid RequestCadastrarMetodo dadosMetodo){
        var metodoCadastrado = metodoService.cadastrarMetodo(dadosMetodo);

        return responseHandler.generateResponse("Cadastrado com sucesso", true, HttpStatus.CREATED, new ResponseMetodo(metodoCadastrado));
    }

    @PutMapping
    public ResponseEntity<Object> editarMetodo(@RequestBody @Valid RequestEditarMetodo dadosMetodo){
        var metodoEditado = metodoService.editarMaterial(dadosMetodo);

        return responseHandler.generateResponse("Editado com sucesso", true, HttpStatus.OK, new ResponseMetodo(metodoEditado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarMetodo(@PathVariable Long id){
        metodoService.deletarMetodo(id);

        return responseHandler.generateResponse("Deletado com sucesso", true, HttpStatus.OK, null);
    }

}
