package com.porto.HealthLabApi.domain.bioquimico.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RequestCadastrarBioquimico(
    @NotBlank
    @Size(max = 50)
    String nome,
    @NotNull
    Long usuarioId,
    @NotNull
    String assinatura
) {
    
}
