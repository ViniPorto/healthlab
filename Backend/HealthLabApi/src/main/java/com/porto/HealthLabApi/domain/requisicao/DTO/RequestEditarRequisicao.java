package com.porto.HealthLabApi.domain.requisicao.DTO;

import jakarta.validation.constraints.NotNull;

public record RequestEditarRequisicao(
    @NotNull
    Long requisicaoId,
    boolean urgente,
    boolean paga,
    Long[] examesId
) {

}
