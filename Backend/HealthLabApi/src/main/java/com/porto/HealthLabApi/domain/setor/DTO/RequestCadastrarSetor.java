package com.porto.HealthLabApi.domain.setor.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RequestCadastrarSetor(
    @NotBlank
    @Size(max = 50)
    String nome,
    String descricao
) {

}
