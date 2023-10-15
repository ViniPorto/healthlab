package com.porto.HealthLabApi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario detalharUsuario(Long id) {
        return usuarioRepository.getReferenceById(id);
    }

    public Usuario cadastrarUsuario(@Valid RequestCadastrarUsuario dadosUsuario) {
        var novoUsuario = new Usuario(dadosUsuario);
        novoUsuario.setSenha(passwordEncoder.encode(dadosUsuario.senha()));
        return usuarioRepository.save(novoUsuario);
    }

    public Usuario editarUsuario(@Valid RequestEditarUsuario dadosUsuario) {
        var usuario = usuarioRepository.getReferenceById(dadosUsuario.id());
        usuario.atualizarInformacoes(dadosUsuario);
        if(dadosUsuario.senha() != null){
            usuario.setSenha(passwordEncoder.encode(dadosUsuario.senha()));
        }
        return usuarioRepository.save(usuario);
    }

    public void inativarUsuario(Long id) {
        var usuario = usuarioRepository.getReferenceById(id);
        usuario.inativar();
        usuarioRepository.save(usuario);
    }

    public void reativarUsuario(Long id) {
        var usuario = usuarioRepository.getReferenceById(id);
        usuario.ativar();
        usuarioRepository.save(usuario);
    }

    public void deletarUsuario(Long id) {
        var usuario = usuarioRepository.getReferenceById(id);
        System.out.println(usuario.getNome()); //verificar por que o Spring não lança a exceção ao deletar um user inexistente, porém lança ao printar isto
        usuarioRepository.delete(usuario);
    }

    public void elegerAdministrador(Long id) {
        var usuario = usuarioRepository.getReferenceById(id);
        usuario.tornarAdministrador();
        usuarioRepository.save(usuario);
    }

}
