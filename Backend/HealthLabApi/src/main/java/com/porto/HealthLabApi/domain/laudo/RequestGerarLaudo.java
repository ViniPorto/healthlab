package com.porto.HealthLabApi.domain.laudo;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RequestGerarLaudo(
    @NotNull
    @NotEmpty
    List<Long> requisicoesId
) {
    
}
