package com.porto.HealthLabApi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.porto.HealthLabApi.domain.requisicao.RequisicaoExame;
import com.porto.HealthLabApi.domain.requisicao.RequisicaoExameItensResultado;

public interface RequisicaoExameItensResultadoRepository extends JpaRepository<RequisicaoExameItensResultado, Long> {

    List<RequisicaoExameItensResultado> findByRequisicaoExame(RequisicaoExame requisicaoExame);

    void deleteByRequisicaoExame(RequisicaoExame requisicaoExame);
    
}
