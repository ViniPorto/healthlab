package com.porto.HealthLabApi.infra.security.DTO;

import jakarta.validation.constraints.NotBlank;

public record RequestAutenticacaoUsuario(
    @NotBlank
    String login,
    @NotBlank
    String senha
) {

}
