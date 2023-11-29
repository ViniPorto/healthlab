package com.porto.HealthLabApi.domain.requisicao.DTO;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public record RequestEditarRequisicao(
    @NotNull
    Long requisicaoId,
    boolean urgente,
    boolean paga,
    List<Long> examesId
) {

}
