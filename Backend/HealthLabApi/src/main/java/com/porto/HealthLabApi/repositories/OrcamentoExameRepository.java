package com.porto.HealthLabApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.porto.HealthLabApi.domain.orcamento.OrcamentoExame;

@Repository
public interface OrcamentoExameRepository extends JpaRepository<OrcamentoExame, Long> {
    
}
