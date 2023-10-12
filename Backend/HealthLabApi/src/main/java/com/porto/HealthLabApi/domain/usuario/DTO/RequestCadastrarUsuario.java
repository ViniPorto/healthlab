package com.porto.HealthLabApi.domain.usuario.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RequestCadastrarUsuario(
    @NotBlank
    String nome,
    @NotBlank
    @Size(max = 50)
    String login,
    @NotBlank
    @Size(max = 50)
    String senha
) {

}
