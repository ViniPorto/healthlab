package com.porto.HealthLabApi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.porto.HealthLabApi.domain.medico.Medico;
import com.porto.HealthLabApi.domain.medico.DTO.RequestCadastrarMedico;
import com.porto.HealthLabApi.domain.medico.DTO.RequestEditarMedico;
import com.porto.HealthLabApi.infra.exception.exceptions.MedicoJaCadastradoException;
import com.porto.HealthLabApi.repositories.MedicoRepository;

import jakarta.transaction.Transactional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public Page<Medico> listarMedicos(Pageable paginacao, String nome, String crm) {
        return medicoRepository.findAll(paginacao, nome, crm);
    }

    public Medico detalharMedico(Long id) {
        return medicoRepository.findById(id).get();
    }

    @Transactional
    public Medico cadastrarMedico(RequestCadastrarMedico dadosMedico) {
        if(medicoRepository.existsByCrmAndUf(dadosMedico.crm(), dadosMedico.uf())){
            throw new MedicoJaCadastradoException();
        }

        var medico = new Medico(dadosMedico);

        return medicoRepository.save(medico);
    }

    @Transactional
    public Medico editarMedico(RequestEditarMedico dadosMedico) {
        var medico = medicoRepository.findById(dadosMedico.id()).get();
        medico.atualizarInformacoes(dadosMedico);

        return medicoRepository.save(medico);
    }

    @Transactional
    public void deletarMedico(Long id) {
        var medico = medicoRepository.findById(id).get();

        medicoRepository.delete(medico);
    }

}
