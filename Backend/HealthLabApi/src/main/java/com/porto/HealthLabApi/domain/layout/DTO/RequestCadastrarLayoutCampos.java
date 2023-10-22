package com.porto.HealthLabApi.domain.layout.DTO;

import com.porto.HealthLabApi.domain.layout.FonteCor;
import com.porto.HealthLabApi.domain.layout.TipoCampo;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RequestCadastrarLayoutCampos(
    @NotNull
    @Enumerated
    TipoCampo tipoCampo,
    @NotNull
    @Size(min = 1, max = 29)
    Integer altura,
    @NotNull
    @Size(min = 1, max = 21)
    Integer largura,
    String texto,
    @NotNull
    @Enumerated
    FonteCor fonteCor,
    @NotNull
    @Size(min = 1, max = 5)
    Integer fonteTamanho,
    @NotNull
    @Size(min = 1, max = 609)
    Integer posicao
) {
    
}
