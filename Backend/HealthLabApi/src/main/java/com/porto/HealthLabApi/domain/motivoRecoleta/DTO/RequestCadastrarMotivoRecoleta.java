package com.porto.HealthLabApi.domain.motivoRecoleta.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RequestCadastrarMotivoRecoleta(
    @NotBlank
    @Size(max = 50)
    String nome,
    String descricao
) {

}
