package com.porto.HealthLabApi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.porto.HealthLabApi.domain.historico.Historico;
import com.porto.HealthLabApi.repositories.HistoricoRepository;

@Service
public class HistoricoService {
    
    @Autowired
    private HistoricoRepository historicoRepository;

    public List<Historico> listarHistoricos(Pageable paginacao, String tabela, Long referenciaId) {
        return historicoRepository.findByTabelaAndReferenciaId(paginacao, tabela, referenciaId);
    }

}
