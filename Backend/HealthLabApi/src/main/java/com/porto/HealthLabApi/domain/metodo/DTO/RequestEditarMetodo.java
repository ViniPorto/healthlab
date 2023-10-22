package com.porto.HealthLabApi.domain.metodo.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RequestEditarMetodo(
    @NotNull
    Long id,
    @Size(max = 50)
    String nome,
    String descricao
) {

}
