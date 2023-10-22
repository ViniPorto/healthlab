package com.porto.HealthLabApi.domain.setor.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RequestEditarSetor(
    @NotNull
    Long id,
    @Size(max = 50)
    String nome,
    String descricao
) {

}
