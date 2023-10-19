package com.porto.HealthLabApi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.porto.HealthLabApi.domain.usuario.Usuario;
import com.porto.HealthLabApi.domain.usuario.DTO.RequestCadastrarUsuario;
import com.porto.HealthLabApi.domain.usuario.DTO.RequestEditarUsuario;
import com.porto.HealthLabApi.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario detalharUsuario(Long id) {
        return usuarioRepository.findById(id).get();
    }

    @Transactional
    public Usuario cadastrarUsuario(@Valid RequestCadastrarUsuario dadosUsuario) {
        var novoUsuario = new Usuario(dadosUsuario);
        novoUsuario.setSenha(passwordEncoder.encode(dadosUsuario.senha()));
        return usuarioRepository.save(novoUsuario);
    }

    @Transactional
    public Usuario editarUsuario(@Valid RequestEditarUsuario dadosUsuario) {
        var usuario = usuarioRepository.findById(dadosUsuario.id()).get();
        usuario.atualizarInformacoes(dadosUsuario);
        if(dadosUsuario.senha() != null){
            usuario.setSenha(passwordEncoder.encode(dadosUsuario.senha()));
        }
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void inativarUsuario(Long id) {
        var usuario = usuarioRepository.findById(id).get();
        usuario.inativar();
        usuarioRepository.save(usuario);
    }

    @Transactional
    public void reativarUsuario(Long id) {
        var usuario = usuarioRepository.findById(id).get();
        usuario.ativar();
        usuarioRepository.save(usuario);
    }

    @Transactional
    public void deletarUsuario(Long id) {
        var usuario = usuarioRepository.findById(id).get();
        
        usuarioRepository.delete(usuario);
    }

    @Transactional
    public void elegerAdministrador(Long id) {
        var usuario = usuarioRepository.findById(id).get();
        usuario.tornarAdministrador();
        usuarioRepository.save(usuario);
    }

}
