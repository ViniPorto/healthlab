package com.porto.HealthLabApi.domain.metodo.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RequestCadastrarMetodo(
    @NotBlank
    @Size(max = 50)
    String nome,
    String descricao
) {

}
