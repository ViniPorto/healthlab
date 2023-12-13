package com.porto.HealthLabApi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.porto.HealthLabApi.domain.agendamentoColeta.AgendamentoColeta;
import com.porto.HealthLabApi.domain.agendamentoColeta.DTO.RequestCadastrarAgendamentoColeta;
import com.porto.HealthLabApi.infra.exception.exceptions.AgendamentoJaCadastradoNoHorarioException;
import com.porto.HealthLabApi.repositories.AgendamentoColetaRepository;
import com.porto.HealthLabApi.repositories.PessoaRepository;
import com.porto.HealthLabApi.repositories.RequisicaoRepository;
import com.porto.HealthLabApi.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class AgendamentoColetaService {
    
    @Autowired
    private AgendamentoColetaRepository agendamentoColetaRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private RequisicaoRepository requisicaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<AgendamentoColeta> listarAgendamentosColeta(Pageable paginacao, String nome, Long requisicaoId) {
        return agendamentoColetaRepository.findAll(paginacao, nome, requisicaoId);
    }

    @Transactional
    public AgendamentoColeta cadastrarAgendamentoColeta(RequestCadastrarAgendamentoColeta dadosAgendamentoColeta) {
        if(agendamentoColetaRepository.existsByDataHoraColeta(dadosAgendamentoColeta.dataHoraColeta())){
            throw new AgendamentoJaCadastradoNoHorarioException("" + dadosAgendamentoColeta.dataHoraColeta());
        }
        var pessoa = pessoaRepository.findById(dadosAgendamentoColeta.pessoaId()).get();
        var requisicao = requisicaoRepository.findById(dadosAgendamentoColeta.requisicaoId()).get();
        var usuario = usuarioRepository.findById(dadosAgendamentoColeta.usuarioId()).get();
        var agendamentoColeta = new AgendamentoColeta(dadosAgendamentoColeta, pessoa, requisicao, usuario);

        return agendamentoColetaRepository.save(agendamentoColeta);
    }

    @Transactional
    public void deletarAgendamentoColeta(Long id) {
        var agendamentoColeta = agendamentoColetaRepository.findById(id).get();

        agendamentoColetaRepository.delete(agendamentoColeta);
    }
    
}
