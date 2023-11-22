package com.porto.HealthLabApi.domain.requisicao.DTO;

import jakarta.validation.constraints.NotNull;

public record RequestCadastrarRequisicao(
    @NotNull
    Long pessoaId,
    Long medicoId,
    @NotNull
    boolean urgente,
    @NotNull
    Integer usuarioId,
    @NotNull
    boolean paga,
    @NotNull
    Long[] examesId
) {

}
