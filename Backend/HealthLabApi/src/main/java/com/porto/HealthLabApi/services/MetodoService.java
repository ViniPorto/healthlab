package com.porto.HealthLabApi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.porto.HealthLabApi.domain.metodo.Metodo;
import com.porto.HealthLabApi.domain.metodo.DTO.RequestCadastrarMetodo;
import com.porto.HealthLabApi.domain.metodo.DTO.RequestEditarMetodo;
import com.porto.HealthLabApi.repositories.MetodoRepository;

import jakarta.transaction.Transactional;

@Service
public class MetodoService {
    
    @Autowired
    private MetodoRepository metodoRepository;

    public Page<Metodo> listarMetodos(Pageable paginacao, String nome) {
        return metodoRepository.findAll(paginacao, nome);
    }

    public Metodo detalharMetodo(Long id) {
        return metodoRepository.findById(id).get();
    }

    @Transactional
    public Metodo cadastrarMetodo(RequestCadastrarMetodo dadosMetodo) {
        var metodo = new Metodo(dadosMetodo);

        return metodoRepository.save(metodo);
    }

    @Transactional
    public Metodo editarMaterial(RequestEditarMetodo dadosMetodo) {
        var metodo = metodoRepository.findById(dadosMetodo.id()).get();
        metodo.atualizarInformacoes(dadosMetodo);

        return metodoRepository.save(metodo);
    }

    @Transactional
    public void deletarMetodo(Long id) {
        var metodo = metodoRepository.findById(id).get();

        metodoRepository.delete(metodo);
    }

}
