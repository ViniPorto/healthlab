package com.porto.HealthLabApi.controllers;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.porto.HealthLabApi.domain.usuario.Usuario;
import com.porto.HealthLabApi.domain.usuario.DTO.RequestCadastrarUsuario;
import com.porto.HealthLabApi.domain.usuario.DTO.RequestEditarUsuario;
import com.porto.HealthLabApi.domain.usuario.DTO.ResponseUsuario;
import com.porto.HealthLabApi.services.UsuarioService;
import com.porto.HealthLabApi.utils.ResponseHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ResponseHandler responseHandler;

    @GetMapping
    public ResponseEntity<?> listarUsuarios(){
        var usuarios = usuarioService.listarUsuarios().stream().map(usuario -> new ResponseUsuario(usuario)).collect(Collectors.toList());

        return responseHandler.generateResponse("Consulta realizada com sucesso", true, HttpStatus.OK, usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalharUsuario(@PathVariable Long id){
        var usuario = usuarioService.detalharUsuario(id);

        return responseHandler.generateResponse("Consulta realizada com sucesso", true, HttpStatus.OK, new ResponseUsuario(usuario));
    }

    @PostMapping
    public ResponseEntity<?> cadastrarUsuario(@RequestBody @Valid RequestCadastrarUsuario dadosUsuario,
                                                   @AuthenticationPrincipal Usuario usuario){
        var usuarioCriado = usuarioService.cadastrarUsuario(dadosUsuario, usuario);
        
        return responseHandler.generateResponse("Cadastrado com sucesso", true, HttpStatus.CREATED, new ResponseUsuario(usuarioCriado));
    }

    @PutMapping
    public ResponseEntity<?> editarUsuario(@RequestBody @Valid RequestEditarUsuario dadosUsuario,
                                                @AuthenticationPrincipal Usuario usuario){
        var usuarioEditado = usuarioService.editarUsuario(dadosUsuario, usuario);
        
        return responseHandler.generateResponse("Editado com sucesso", true, HttpStatus.OK, new ResponseUsuario(usuarioEditado));
    }

    @DeleteMapping("/inativar/{id}")
    public ResponseEntity<?> inativarUsuario(@PathVariable Long id,
                                                  @AuthenticationPrincipal Usuario usuario){
        usuarioService.inativarUsuario(id, usuario);

        return responseHandler.generateResponse("Inativado com sucesso", true, HttpStatus.OK, null);
    }

    @PostMapping("/reativar/{id}")
    public ResponseEntity<?> reativarUsuario(@PathVariable Long id,
                                                  @AuthenticationPrincipal Usuario usuario){
        usuarioService.reativarUsuario(id, usuario);

        return responseHandler.generateResponse("Reativado com sucesso", true, HttpStatus.OK, null);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable Long id){
        usuarioService.deletarUsuario(id);

        return responseHandler.generateResponse("Deletado com sucesso", true, HttpStatus.OK, null);
    }

    @PostMapping("/elegerAdministrador/{id}")
    public ResponseEntity<?> elegerAdministrador(@PathVariable Long id,
                                                      @AuthenticationPrincipal Usuario usuario){
        usuarioService.elegerAdministrador(id, usuario);

        return responseHandler.generateResponse("Eleito administrador com sucesso", true, HttpStatus.OK, null);
    }

    //HttpStatus.OK -> poderia ser substituido por HttpStatus.NO_CONTENT em alguns métodos
    
}
