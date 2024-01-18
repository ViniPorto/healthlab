package com.porto.HealthLabApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.porto.HealthLabApi.domain.usuario.Usuario;
import com.porto.HealthLabApi.infra.security.DTO.RequestAutenticacaoUsuario;
import com.porto.HealthLabApi.infra.security.DTO.ResponseToken;
import com.porto.HealthLabApi.services.TokenService;
import com.porto.HealthLabApi.utils.ResponseHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ResponseHandler responseHandler;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> efetuarLogin(@RequestBody @Valid RequestAutenticacaoUsuario dados){
        var tokenSpring = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = authenticationManager.authenticate(tokenSpring);
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        var token = new ResponseToken(tokenJWT);

        return responseHandler.generateResponse("Autenticado com sucesso", true, HttpStatus.OK, token);
    }

}
