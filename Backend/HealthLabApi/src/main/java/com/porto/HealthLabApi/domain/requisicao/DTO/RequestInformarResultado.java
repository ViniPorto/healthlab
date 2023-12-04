package com.porto.HealthLabApi.domain.requisicao.DTO;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public record RequestInformarResultado(
    @NotNull
    Long requisicaoExameId,
    List<RequestInformarResultadoRequisicaoExameItensResultado> camposResultado
) {

}
