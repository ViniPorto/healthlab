package com.porto.HealthLabApi.domain.motivoRecoleta.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RequestEditarMotivoRecoleta(
    @NotNull
    Long id,
    @Size(max = 50)
    String nome,
    String descricao
) {
    
}
