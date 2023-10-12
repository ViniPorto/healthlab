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
        return usuarioRepository.findAll();
    }

    public Usuario detalharUsuario(Long id) {
        return usuarioRepository.getReferenceById(id);
    }

    public Usuario cadastrarUsuario(@Valid RequestCadastrarUsuario dadosUsuario) {
        var novoUsuario = new Usuario(dadosUsuario);
        return usuarioRepository.save(novoUsuario);
    }

    public Usuario editarUsuario(@Valid RequestEditarUsuario dadosUsuario) {
        var usuario = usuarioRepository.getReferenceById(dadosUsuario.id());
        usuario.atualizarInformacoes(dadosUsuario);
        return usuarioRepository.save(usuario);
    }

    public void inativarUsuario(Long id) {
        var usuario = usuarioRepository.getReferenceById(id);
        //caso usuário a ser inativo == administrador, então o usuário que está inativando também deve ser administrador
        usuario.inativar();
        usuarioRepository.save(usuario);
    }

    public void reativarUsuario(Long id) {
        var usuario = usuarioRepository.getReferenceById(id);
        //caso usuário a ser ativado == administrador, então o usuário que está ativando também deve ser administrador
        usuario.ativar();
        usuarioRepository.save(usuario);
    }

    public void deletarUsuario(Long id) {
        //caso usuário a ser deletado == administrador, então o usuário que está deletando também deve ser administrador
        usuarioRepository.deleteById(id);
    }

    public void elegerAdministrador(Long id) {
        //usuário que está delegando o outro como administrador deve ser administrador
        var usuario = usuarioRepository.getReferenceById(id);
        usuario.tornarAdministrador();
        usuarioRepository.save(usuario);
    }

}
