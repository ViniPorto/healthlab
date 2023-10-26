package com.porto.HealthLabApi.domain.exame.DTO;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RequestEditarExame(
    @NotNull
    Long exameId,
    Long setorId,
    Long metodoId,
    Long materialId,
    Boolean principal,
    @Size(max = 50)
    String titulo,
    @Size(min = 3, max = 5)
    String sigla,
    Integer tempoExecucaoNormal,
    Integer tempoExecucaoUrgente,
    String descricao,
    BigDecimal preco
) {

}
