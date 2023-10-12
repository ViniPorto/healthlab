package com.porto.HealthLabApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.porto.HealthLabApi.domain.usuario.DTO.RequestCadastrarUsuario;
import com.porto.HealthLabApi.domain.usuario.DTO.RequestEditarUsuario;
import com.porto.HealthLabApi.domain.usuario.DTO.ResponseUsuario;
import com.porto.HealthLabApi.infra.ResponseHandler;
import com.porto.HealthLabApi.services.UsuarioService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ResponseHandler responseHandler;

    @GetMapping
    public ResponseEntity<Object> listarUsuarios(){
        return responseHandler.generateResponse("Consulta realizada com sucesso", HttpStatus.OK, ResponseUsuario.toListResponseUsuario(usuarioService.listarUsuarios()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> detalharUsuario(@PathVariable Long id){
        return responseHandler.generateResponse("Consulta realizada com sucesso", HttpStatus.OK, new ResponseUsuario(usuarioService.detalharUsuario(id)));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Object> cadastrarUsuario(@RequestBody @Valid RequestCadastrarUsuario dadosUsuario){
        var usuarioCriado = usuarioService.cadastrarUsuario(dadosUsuario);
        
        return responseHandler.generateResponse("Cadastrado com sucesso", HttpStatus.CREATED, new ResponseUsuario(usuarioCriado));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Object> editarUsuario(@RequestBody @Valid RequestEditarUsuario dadosUsuario){
        var usuarioEditado = usuarioService.editarUsuario(dadosUsuario);
        
        return responseHandler.generateResponse("Editado com sucesso", HttpStatus.OK, new ResponseUsuario(usuarioEditado));
    }

    @DeleteMapping("/inativar/{id}")
    @Transactional
    public ResponseEntity<Object> inativarUsuario(@PathVariable Long id){
        usuarioService.inativarUsuario(id);

        return responseHandler.generateResponse("Inativado com sucesso", HttpStatus.OK, null);
    }

    @PostMapping("/reativar/{id}")
    @Transactional
    public ResponseEntity<Object> reativarUsuario(@PathVariable Long id){
        usuarioService.reativarUsuario(id);

        return responseHandler.generateResponse("Reativado com sucesso", HttpStatus.OK, null);
    }

    @DeleteMapping("/deletar/{id}")
    @Transactional
    public ResponseEntity<Object> deletarUsuario(@PathVariable Long id){
        usuarioService.deletarUsuario(id);

        return responseHandler.generateResponse("Deletado com sucesso", HttpStatus.OK, null);
    }

    @PostMapping("/elegerAdministrador/{id}")
    @Transactional
    public ResponseEntity<Object> elegerAdministrador(@PathVariable Long id){
        usuarioService.elegerAdministrador(id);

        return responseHandler.generateResponse("Eleito administrador com sucesso", HttpStatus.OK, null);
    }

    //HttpStatus.OK -> poderia ser substituido por HttpStatus.NO_CONTENT em alguns métodos, 
    //porém para fins estudantis e para testes estou utilizando o status "fora do padrão"
    
}
