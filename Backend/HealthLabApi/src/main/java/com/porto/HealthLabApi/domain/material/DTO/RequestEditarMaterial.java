package com.porto.HealthLabApi.domain.material.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RequestEditarMaterial(
    @NotNull
    Long id,
    @Size(max = 50)
    String nome,
    String descricao
) {

}
