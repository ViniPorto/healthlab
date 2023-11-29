package com.porto.HealthLabApi.domain.requisicao.DTO;

import java.util.List;

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
    List<Long> examesId
) {

}
