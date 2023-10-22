package com.porto.HealthLabApi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.porto.HealthLabApi.domain.setor.Setor;
import com.porto.HealthLabApi.domain.setor.DTO.RequestCadastrarSetor;
import com.porto.HealthLabApi.domain.setor.DTO.RequestEditarSetor;
import com.porto.HealthLabApi.repositories.SetorRepository;

import jakarta.transaction.Transactional;

@Service
public class SetorService {
    
    @Autowired
    private SetorRepository setorRepository;

    public Page<Setor> listarSetores(Pageable paginacao, String nome) {
        return setorRepository.findAll(paginacao, nome);
    }

    public Setor detalharSetor(Long id) {
        return setorRepository.findById(id).get();
    }

    @Transactional
    public Setor cadastrarSetor(RequestCadastrarSetor dadosSetor) {
        var setor = new Setor(dadosSetor);

        return setorRepository.save(setor);
    }

    @Transactional
    public Setor editarSetor(RequestEditarSetor dadosSetor) {
        var setor = setorRepository.findById(dadosSetor.id()).get();
        setor.atualizarInformacoes(dadosSetor);

        return setorRepository.save(setor);
    }

    @Transactional
    public void deletarSetor(Long id) {
        var setor = setorRepository.findById(id).get();

        setorRepository.delete(setor);
    }

}
