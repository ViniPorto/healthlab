package com.porto.HealthLabApi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.porto.HealthLabApi.domain.requisicao.Requisicao;
import com.porto.HealthLabApi.domain.requisicao.RequisicaoExame;

public interface RequisicaoExameRepository extends JpaRepository<RequisicaoExame, Long> {

    List<RequisicaoExame> findByRequisicao(Requisicao requisicao);
    
}
