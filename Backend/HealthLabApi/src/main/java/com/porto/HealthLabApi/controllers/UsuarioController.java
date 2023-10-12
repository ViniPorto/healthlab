package com.porto.HealthLabApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.porto.HealthLabApi.domain.usuario.DTO.RequestCadastrarUsuario;
import com.porto.HealthLabApi.domain.usuario.DTO.RequestEditarUsuario;
import com.porto.HealthLabApi.domain.usuario.DTO.ResponseUsuario;
import com.porto.HealthLabApi.services.UsuarioService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<ResponseUsuario>> listarUsuarios(){
        return ResponseEntity.ok(ResponseUsuario.toListResponseUsuario(usuarioService.listarUsuarios()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUsuario> detalharUsuario(@PathVariable Long id){
        return ResponseEntity.ok(new ResponseUsuario(usuarioService.detalharUsuario(id)));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseUsuario> cadastrarUsuario(@RequestBody @Valid RequestCadastrarUsuario dadosUsuario, UriComponentsBuilder uriBuilder){
        var usuarioCriado = usuarioService.cadastrarUsuario(dadosUsuario);
        
        var uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuarioCriado.getId()).toUri();

        return ResponseEntity.created(uri).body(new ResponseUsuario(usuarioCriado));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ResponseUsuario> editarUsuario(@RequestBody @Valid RequestEditarUsuario dadosUsuario){
        var usuarioEditado = usuarioService.editarUsuario(dadosUsuario);
        
        return ResponseEntity.ok(new ResponseUsuario(usuarioEditado));
    }

    @DeleteMapping("/inativar/{id}")
    @Transactional
    public ResponseEntity<Void> inativarUsuario(@PathVariable Long id){
        usuarioService.inativarUsuario(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/reativar/{id}")
    @Transactional
    public ResponseEntity<Void> reativarUsuario(@PathVariable Long id){
        usuarioService.reativarUsuario(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deletar/{id}")
    @Transactional
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id){
        usuarioService.deletarUsuario(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/elegerAdministrador/{id}")
    @Transactional
    public ResponseEntity<Void> elegerAdministrador(@PathVariable Long id){
        usuarioService.elegerAdministrador(id);

        return ResponseEntity.noContent().build();
    }
}
