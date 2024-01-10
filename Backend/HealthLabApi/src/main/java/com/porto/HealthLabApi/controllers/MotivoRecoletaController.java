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
import org.springframework.web.bind.annotation.RestController;

import com.porto.HealthLabApi.domain.motivoRecoleta.DTO.RequestCadastrarMotivoRecoleta;
import com.porto.HealthLabApi.domain.motivoRecoleta.DTO.RequestEditarMotivoRecoleta;
import com.porto.HealthLabApi.domain.motivoRecoleta.DTO.ResponseMotivoRecoleta;
import com.porto.HealthLabApi.domain.usuario.Usuario;
import com.porto.HealthLabApi.services.MotivoRecoletaService;
import com.porto.HealthLabApi.utils.ResponseHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/motivorecoleta")
public class MotivoRecoletaController {
    
    @Autowired
    private MotivoRecoletaService motivoRecoletaService;

    @Autowired
    private ResponseHandler responseHandler;

    @GetMapping
    public ResponseEntity<Object> listarMotivosRecoleta(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        var motivosRecoleta = motivoRecoletaService.listarMotivosRecoleta(paginacao).map(ResponseMotivoRecoleta::new);

        return responseHandler.generateResponse("Consulta realizada com sucesso", true, HttpStatus.OK, motivosRecoleta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> detalharMotivoRecoleta(@PathVariable Long id){
        var motivoRecoleta = motivoRecoletaService.detalharMotivoRecoleta(id);

        return responseHandler.generateResponse("Consulta realizada com sucesso", true, HttpStatus.OK, new ResponseMotivoRecoleta(motivoRecoleta));
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarMotivoRecoleta(@RequestBody @Valid RequestCadastrarMotivoRecoleta dadosMotivoRecoleta,
                                                          @AuthenticationPrincipal Usuario usuario){
        var motivoRecoletaCriado = motivoRecoletaService.cadastrarMotivoRecoleta(dadosMotivoRecoleta, usuario);

        return responseHandler.generateResponse("Cadastrado com sucesso", true, HttpStatus.CREATED, new ResponseMotivoRecoleta(motivoRecoletaCriado));
    }

    @PutMapping
    public ResponseEntity<Object> editarMotivoRecoleta(@RequestBody @Valid RequestEditarMotivoRecoleta dadosMotivoRecoleta,
                                                       @AuthenticationPrincipal Usuario usuario){
        var motivoRecoletaEditado = motivoRecoletaService.editarMotivoRecoleta(dadosMotivoRecoleta, usuario);

        return responseHandler.generateResponse("Editado com sucesso", true, HttpStatus.OK, new ResponseMotivoRecoleta(motivoRecoletaEditado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarMotivoRecoleta(@PathVariable Long id){
        motivoRecoletaService.deletarMotivoRecoleta(id);

        return responseHandler.generateResponse("Excluido com sucesso", true, HttpStatus.OK, null);
    }

}
