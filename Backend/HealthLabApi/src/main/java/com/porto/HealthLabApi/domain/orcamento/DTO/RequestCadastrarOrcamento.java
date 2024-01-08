package com.porto.HealthLabApi.domain.orcamento.DTO;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RequestCadastrarOrcamento(
    @NotNull
    Long pessoaId,
    Long medicoId,
    @NotNull
    @Future
    LocalDateTime data,
    @NotNull
    @NotEmpty
    List<Long> examesId
) {
    
}
