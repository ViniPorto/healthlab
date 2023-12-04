package com.porto.HealthLabApi.domain.requisicao.DTO;

import com.porto.HealthLabApi.domain.requisicao.RequisicaoExameItensResultado;

public record ResponseRequisicaoExameItensResultado(
    Long requisicaoExameItensResultadoId,
    String requisicaoExameItensResultadoResultado,
    Long requisicaoExameItensResultadoCodigoCampo
) {

    public ResponseRequisicaoExameItensResultado(RequisicaoExameItensResultado itensResultado) {
        this(itensResultado.getId(), itensResultado.getResultado(), itensResultado.getCodigoCampo());
    }
    
}
