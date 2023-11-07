package com.porto.HealthLabApi.controllers;

import java.util.List;

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

import com.porto.HealthLabApi.domain.bioquimico.DTO.RequestCadastrarBioquimico;
import com.porto.HealthLabApi.domain.bioquimico.DTO.RequestEditarBioquimico;
import com.porto.HealthLabApi.domain.bioquimico.DTO.ResponseBioquimico;
import com.porto.HealthLabApi.domain.usuario.DTO.ResponseUsuario;
import com.porto.HealthLabApi.services.BioquimicoService;
import com.porto.HealthLabApi.utils.ResponseHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bioquimico")
public class BioquimicoController {
    
    @Autowired
    private BioquimicoService bioquimicoService;

    @Autowired
    private ResponseHandler responseHandler;

    @GetMapping
    private ResponseEntity<Object> listarBioquimicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao,
                                                    @RequestParam(required = false) String nome){
        List<ResponseBioquimico> bioquimicos = bioquimicoService.listarBioquimicos(paginacao, nome).stream().map(b -> new ResponseBioquimico(b, new ResponseUsuario(b.getUsuario()))).toList();

        return responseHandler.generateResponse("Consulta realizada com sucesso", true, HttpStatus.OK, bioquimicos);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Object> detalharBioquimico(@PathVariable Long id){
        var bioquimico = bioquimicoService.detalharBioquimico(id);

        return responseHandler.generateResponse("Consulta realizada com sucesso", true, HttpStatus.OK, new ResponseBioquimico(bioquimico, new ResponseUsuario(bioquimico.getUsuario())));
    }

    @PostMapping
    private ResponseEntity<Object> cadastrarBioquimico(@RequestBody @Valid RequestCadastrarBioquimico dadosBioquimico){
        var bioquimicoCriado = bioquimicoService.cadastrarBioquimico(dadosBioquimico);

        return responseHandler.generateResponse("Cadastrado com sucesso", true, HttpStatus.CREATED, new ResponseBioquimico(bioquimicoCriado, new ResponseUsuario(bioquimicoCriado.getUsuario())));
    }

    @PutMapping
    private ResponseEntity<Object> editarBioquimico(@RequestBody @Valid RequestEditarBioquimico dadosBioquimico){
        var bioquimicoEditado = bioquimicoService.editarBioquimico(dadosBioquimico);

        return responseHandler.generateResponse("Editado com sucesso", true, HttpStatus.OK, new ResponseBioquimico(bioquimicoEditado, new ResponseUsuario(bioquimicoEditado.getUsuario())));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Object> deletarBioquimico(@PathVariable Long id){
        bioquimicoService.deletarBioquimico(id);

        return responseHandler.generateResponse("Deletado com sucesso", true, HttpStatus.OK, null);
    }

}
