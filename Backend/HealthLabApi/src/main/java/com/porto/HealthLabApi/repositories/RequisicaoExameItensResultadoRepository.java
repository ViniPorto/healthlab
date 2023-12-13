package com.porto.HealthLabApi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.porto.HealthLabApi.domain.requisicao.RequisicaoExame;
import com.porto.HealthLabApi.domain.requisicao.RequisicaoExameItensResultado;

@Repository
public interface RequisicaoExameItensResultadoRepository extends JpaRepository<RequisicaoExameItensResultado, Long> {

    List<RequisicaoExameItensResultado> findByRequisicaoExame(RequisicaoExame requisicaoExame);

    void deleteByRequisicaoExame(RequisicaoExame requisicaoExame);
    
}
