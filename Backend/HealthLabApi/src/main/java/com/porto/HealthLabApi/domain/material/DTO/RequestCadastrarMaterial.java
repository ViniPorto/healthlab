package com.porto.HealthLabApi.domain.material.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RequestCadastrarMaterial(
    @NotBlank
    @Size(max = 50)
    String nome,
    String descricao
) {

}
