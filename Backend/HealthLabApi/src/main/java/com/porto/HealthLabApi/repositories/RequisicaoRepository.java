package com.porto.HealthLabApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.porto.HealthLabApi.domain.requisicao.Requisicao;

public interface RequisicaoRepository extends JpaRepository<Requisicao, Long> {
    
}
