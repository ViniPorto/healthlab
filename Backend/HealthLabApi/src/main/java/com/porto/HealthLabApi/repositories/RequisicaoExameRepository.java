package com.porto.HealthLabApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.porto.HealthLabApi.domain.requisicao.RequisicaoExame;

public interface RequisicaoExameRepository extends JpaRepository<RequisicaoExame, Long> {
    
}
