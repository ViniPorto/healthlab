package com.porto.HealthLabApi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.porto.HealthLabApi.domain.orcamento.Orcamento;
import com.porto.HealthLabApi.repositories.OrcamentoExameRepository;
import com.porto.HealthLabApi.repositories.OrcamentoRepository;

@Service
public class OrcamentoService {
    
    @Autowired
    private OrcamentoRepository orcamentoRepository;

    @Autowired
    private OrcamentoExameRepository orcamentoExameRepository;

    public Page<Orcamento> listarOrcamentos(Pageable paginacao, String pessoaNome, Integer id) {
        return orcamentoRepository.findAll(paginacao, pessoaNome, id);
    }

    
}
