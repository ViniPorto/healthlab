package com.porto.HealthLabApi.domain.requisicao.DTO;

import jakarta.validation.constraints.NotNull;

public record RequestInformarResultadoRequisicaoExameItensResultado(
    @NotNull
    Long codigoCampo,
    String resultado
) {
    
}
