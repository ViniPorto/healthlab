package com.porto.HealthLabApi.domain.pessoa.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RequestEditarPessoa(
    @NotNull
    Long id,
    String nome,
    String email,
    @Pattern(regexp = "\\d{11}")
    String telefone,
    String dadosGerais, 
    String observacao
) {
    
}
