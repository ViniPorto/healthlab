package com.porto.HealthLabApi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.porto.HealthLabApi.domain.usuario.Usuario;
import com.porto.HealthLabApi.domain.usuario.DTO.RequestCadastrarUsuario;
import com.porto.HealthLabApi.domain.usuario.DTO.RequestEditarUsuario;
import com.porto.HealthLabApi.repositories.UsuarioRepository;

import jakarta.validation.Valid;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarUsuarios() {
        return null;
    }

    public Usuario detalharUsuario(Long id) {
        return null;
    }

    public Usuario cadastrarUsuario(@Valid RequestCadastrarUsuario dadosUsuario) {
        return null;
    }

    public Usuario editarUsuario(@Valid RequestEditarUsuario dadosUsuario) {
        return null;
    }

    public void inativarUsuario(Long id) {
    }

    public void reativarUsuario(Long id) {
    }

    public void deletarUsuario(Long id) {
    }

    public void elegerAdministrador(Long id) {
    }

}
