package com.porto.HealthLabApi.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.porto.HealthLabApi.domain.historico.Historico;
import com.porto.HealthLabApi.domain.usuario.Usuario;
import com.porto.HealthLabApi.domain.usuario.DTO.RequestCadastrarUsuario;
import com.porto.HealthLabApi.domain.usuario.DTO.RequestEditarUsuario;
import com.porto.HealthLabApi.repositories.HistoricoRepository;
import com.porto.HealthLabApi.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private HistoricoRepository historicoRepository;

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario detalharUsuario(Long id) {
        return usuarioRepository.findById(id).get();
    }

    @Transactional
    public Usuario cadastrarUsuario(@Valid RequestCadastrarUsuario dadosUsuario, Usuario usuario) {
        var novoUsuario = new Usuario(dadosUsuario);
        novoUsuario.setSenha(passwordEncoder.encode(dadosUsuario.senha()));
        usuarioRepository.save(novoUsuario);

        historicoRepository.save(new Historico(novoUsuario.getId(), "USUARIO", usuario, "CADASTRO", LocalDateTime.now(), gerarDados(novoUsuario)));

        return novoUsuario;
    }

    @Transactional
    public Usuario editarUsuario(@Valid RequestEditarUsuario dadosUsuario, Usuario usuario2) {
        var usuario = usuarioRepository.findById(dadosUsuario.id()).get();
        usuario.atualizarInformacoes(dadosUsuario);
        if(dadosUsuario.senha() != null){
            usuario.setSenha(passwordEncoder.encode(dadosUsuario.senha()));
        }
        usuarioRepository.save(usuario);

        historicoRepository.save(new Historico(usuario.getId(), "USUARIO", usuario2, "EDIÇÃO", LocalDateTime.now(), gerarDados(usuario)));

        return usuario;
    }

    @Transactional
    public void inativarUsuario(Long id, Usuario usuario2) {
        var usuario = usuarioRepository.findById(id).get();
        usuario.inativar();
        usuarioRepository.save(usuario);

        historicoRepository.save(new Historico(usuario.getId(), "USUARIO", usuario2, "INATIVAÇÃO DE USUÁRIO", LocalDateTime.now(), gerarDados(usuario)));
    }

    @Transactional
    public void reativarUsuario(Long id, Usuario usuario2) {
        var usuario = usuarioRepository.findById(id).get();
        usuario.ativar();
        usuarioRepository.save(usuario);

        historicoRepository.save(new Historico(usuario.getId(), "USUARIO", usuario2, "REATIVAÇÃO DE USUÁRIO", LocalDateTime.now(), gerarDados(usuario)));
    }

    @Transactional
    public void deletarUsuario(Long id) {
        var usuario = usuarioRepository.findById(id).get();
        
        usuarioRepository.delete(usuario);
    }

    @Transactional
    public void elegerAdministrador(Long id, Usuario usuario2) {
        var usuario = usuarioRepository.findById(id).get();
        usuario.tornarAdministrador();
        usuarioRepository.save(usuario);

        historicoRepository.save(new Historico(usuario.getId(), "USUARIO", usuario2, "TORNAR USUÁRIO ADMINISTRADOR", LocalDateTime.now(), gerarDados(usuario)));
    }

    private String gerarDados(Usuario usuario){
        return "NOME: " + usuario.getNome() +
        "\nLOGIN: " + usuario.getLogin() +
        "\nSTATUS: " + usuario.isAtivo() +
        "\nÉ ADMINISTRADOR: " + usuario.isAdministrador();
    }

}
