package com.porto.HealthLabApi.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.porto.HealthLabApi.domain.historico.Historico;
import com.porto.HealthLabApi.domain.metodo.Metodo;
import com.porto.HealthLabApi.domain.metodo.DTO.RequestCadastrarMetodo;
import com.porto.HealthLabApi.domain.metodo.DTO.RequestEditarMetodo;
import com.porto.HealthLabApi.domain.usuario.Usuario;
import com.porto.HealthLabApi.repositories.HistoricoRepository;
import com.porto.HealthLabApi.repositories.MetodoRepository;

import jakarta.transaction.Transactional;

@Service
public class MetodoService {
    
    @Autowired
    private MetodoRepository metodoRepository;

    @Autowired
    private HistoricoRepository historicoRepository;

    public Page<Metodo> listarMetodos(Pageable paginacao, String nome) {
        return metodoRepository.findAll(paginacao, nome);
    }

    public Metodo detalharMetodo(Long id) {
        return metodoRepository.findById(id).get();
    }

    @Transactional
    public Metodo cadastrarMetodo(RequestCadastrarMetodo dadosMetodo, Usuario usuario) {
        var metodo = new Metodo(dadosMetodo);

        metodoRepository.save(metodo);

        historicoRepository.save(new Historico(metodo.getId(), "METODO", usuario, "CADASTRO", LocalDateTime.now(), gerarDados(metodo)));

        return metodo;
    }

    @Transactional
    public Metodo editarMaterial(RequestEditarMetodo dadosMetodo, Usuario usuario) {
        var metodo = metodoRepository.findById(dadosMetodo.id()).get();
        metodo.atualizarInformacoes(dadosMetodo);

        metodoRepository.save(metodo);

        historicoRepository.save(new Historico(metodo.getId(), "METODO", usuario, "EDIÇÃO", LocalDateTime.now(), gerarDados(metodo)));

        return metodo;
    }

    @Transactional
    public void deletarMetodo(Long id) {
        var metodo = metodoRepository.findById(id).get();

        metodoRepository.delete(metodo);
    }

    private String gerarDados(Metodo metodo){
        return "NOME: " + metodo.getNome() +
        "\nDESCRICAO: " + metodo.getDescricao();
    }

}
