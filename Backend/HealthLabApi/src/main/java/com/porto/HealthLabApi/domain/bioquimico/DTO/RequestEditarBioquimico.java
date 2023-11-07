package com.porto.HealthLabApi.domain.bioquimico.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RequestEditarBioquimico(
    @NotNull
    Long id,
    @Size(max = 50)
    String nome,
    String assinatura
) {
    
}
