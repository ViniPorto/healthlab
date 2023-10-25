package com.porto.HealthLabApi.domain.exame.DTO;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RequestCadastrarExame(
    @NotBlank
    @Size(max = 50)
    String titulo,
    @NotBlank
    @Size(min = 3, max = 5)
    String sigla,
    String descricao,
    @NotNull
    Long setorId,
    @NotNull
    Long metodoId,
    @NotNull
    Long materialId,
    @NotNull
    boolean principal,
    @NotNull
    BigDecimal preco,
    @NotNull
    Integer tempoExecucaoNormal,
    @NotNull
    Integer tempoExecucaoUrgente
) {

}
