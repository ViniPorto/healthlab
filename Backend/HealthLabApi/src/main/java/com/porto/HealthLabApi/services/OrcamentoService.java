package com.porto.HealthLabApi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.porto.HealthLabApi.domain.medico.Medico;
import com.porto.HealthLabApi.domain.orcamento.Orcamento;
import com.porto.HealthLabApi.domain.orcamento.OrcamentoExame;
import com.porto.HealthLabApi.domain.orcamento.DTO.RequestCadastrarOrcamento;
import com.porto.HealthLabApi.domain.usuario.Usuario;
import com.porto.HealthLabApi.infra.exception.exceptions.ExameJaCadastradoException;
import com.porto.HealthLabApi.repositories.ExameRepository;
import com.porto.HealthLabApi.repositories.MedicoRepository;
import com.porto.HealthLabApi.repositories.OrcamentoExameRepository;
import com.porto.HealthLabApi.repositories.OrcamentoRepository;
import com.porto.HealthLabApi.repositories.PessoaRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class OrcamentoService {
    
    @Autowired
    private OrcamentoRepository orcamentoRepository;

    @Autowired
    private OrcamentoExameRepository orcamentoExameRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ExameRepository exameRepository;

    public Page<Orcamento> listarOrcamentos(Pageable paginacao, String pessoaNome, Integer id) {
        return orcamentoRepository.findAll(paginacao, pessoaNome, id);
    }

    @Transactional
    public Orcamento cadastrarOrcamento(@Valid RequestCadastrarOrcamento dadosOrcamento, Usuario usuario) {
        Medico medico = null;
        if(dadosOrcamento.medicoId() != null){
           medico = medicoRepository.findById(dadosOrcamento.medicoId()).get();
        }

        var pessoa = pessoaRepository.findById(dadosOrcamento.pessoaId()).get();

        var orcamento = new Orcamento(dadosOrcamento, pessoa, medico, usuario);

        orcamentoRepository.save(orcamento);

        for(Long exameId : dadosOrcamento.examesId()){
            if(orcamentoExameRepository.existsById(exameId, orcamento.getId())){
                throw new ExameJaCadastradoException();
            }
            var exame = exameRepository.findById(exameId).get();

            var orcamentoExame = new OrcamentoExame(orcamento, exame);

            orcamentoExameRepository.save(orcamentoExame);

            orcamento.adicionarExame(orcamentoExame);
        }

        return orcamentoRepository.save(orcamento);
    }

    @Transactional
    public void deletarOrcamento(Long id) {
        var orcamento = orcamentoRepository.findById(id).get();

        for(OrcamentoExame orcamentoExame : orcamento.getOrcamentoExames()){
            orcamentoExameRepository.delete(orcamentoExame);
        }

        orcamentoRepository.delete(orcamento);
    }
  
}
