package com.porto.HealthLabApi.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.porto.HealthLabApi.domain.historico.Historico;
import com.porto.HealthLabApi.domain.medico.Medico;
import com.porto.HealthLabApi.domain.medico.DTO.RequestCadastrarMedico;
import com.porto.HealthLabApi.domain.medico.DTO.RequestEditarMedico;
import com.porto.HealthLabApi.domain.usuario.Usuario;
import com.porto.HealthLabApi.infra.exception.exceptions.MedicoJaCadastradoException;
import com.porto.HealthLabApi.repositories.HistoricoRepository;
import com.porto.HealthLabApi.repositories.MedicoRepository;

import jakarta.transaction.Transactional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private HistoricoRepository historicoRepository;

    public Page<Medico> listarMedicos(Pageable paginacao, String nome, String crm) {
        return medicoRepository.findAll(paginacao, nome, crm);
    }

    public Medico detalharMedico(Long id) {
        return medicoRepository.findById(id).get();
    }

    @Transactional
    public Medico cadastrarMedico(RequestCadastrarMedico dadosMedico, Usuario usuario) {
        if(medicoRepository.existsByCrmAndUf(dadosMedico.crm(), dadosMedico.uf())){
            throw new MedicoJaCadastradoException();
        }

        var medico = new Medico(dadosMedico);

        medicoRepository.save(medico);

        historicoRepository.save(new Historico(medico.getId(), "MEDICO", usuario, "CADASTRO", LocalDateTime.now(), gerarDados(medico)));

        return medico;
    }

    @Transactional
    public Medico editarMedico(RequestEditarMedico dadosMedico, Usuario usuario) {
        var medico = medicoRepository.findById(dadosMedico.id()).get();
        medico.atualizarInformacoes(dadosMedico);

        medicoRepository.save(medico);

        historicoRepository.save(new Historico(medico.getId(), "MEDICO", usuario, "EDIÇÃO", LocalDateTime.now(), gerarDados(medico)));

        return medico;
    }

    @Transactional
    public void deletarMedico(Long id) {
        var medico = medicoRepository.findById(id).get();

        medicoRepository.delete(medico);
    }

    private String gerarDados(Medico medico){
        return "NOME: " + medico.getNome() +
        "\nCRM: " + medico.getCrm() +
        "\nEMAIL: " + medico.getEmail() +
        "\nTELEFONE: " + medico.getTelefone() +
        "\nUF: " + medico.getUf();
    }

}
