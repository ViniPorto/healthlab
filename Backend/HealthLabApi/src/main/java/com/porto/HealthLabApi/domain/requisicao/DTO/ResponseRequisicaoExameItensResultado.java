package com.porto.HealthLabApi.domain.requisicao.DTO;

public record ResponseRequisicaoExameItensResultado(
    Long requisicaoExameItensResultadoId,
    String requisicaoExameItensResultadoResultado,
    String requisicaoExameItensResultadoObservacao
) {
    
}
