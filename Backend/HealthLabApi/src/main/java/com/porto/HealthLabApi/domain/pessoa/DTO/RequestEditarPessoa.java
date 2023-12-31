package com.porto.HealthLabApi.domain.pessoa.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RequestEditarPessoa(
    @NotNull
    Long id,
    @Size(max = 50)
    String nome,
    @Size(max = 50)
    String email,
    @Pattern(regexp = "\\d{11}")
    String telefone,
    @Size(max = 300)
    String dadosGerais, 
    @Size(max = 300)
    String observacao
) {
    
}
